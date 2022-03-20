package com.bitc.wub.service;

import java.util.List;


import com.bitc.wub.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bitc.wub.common.FileUtils;
import com.bitc.wub.mapper.ArticleMapper;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private FileUtils fileUtils;

	// 상세 글 보기
	@Override
	public ArticleDto selectArticleDetail(int bookIdx) throws Exception {
		
		// 조회수 상승
		articleMapper.countHitCnt(bookIdx);
		
		// 내부에 userIdx 존재
		ArticleDto article = articleMapper.selectArticleDetail(bookIdx);

		// 게시한 책의 분류
		article.setMainCategory(articleMapper.selectMainCategory(article.getMainCategory()));
		article.setDetailCategory(articleMapper.selectDetailCategory(article.getDetailCategory()));

		// 글쓴이의 정보
		article.setUserLocal(articleMapper.selectUserLocalInfo(article.getUserIdx()));
		article.setUserName(articleMapper.selectUserInfo(article.getUserIdx()));

		// 게시글 추천 수
		article.setBookRecommend(articleMapper.selectCountRecommend(bookIdx));

		// 다른 테이블 참조
		//userDto user = articleMapper.selectUserLocal(article.getUserIdx());
		//article.setUserName(user.getUserName);
		//article.setUserLocal(user.getUserLocal);
		
		List<ImgDto> imgList = articleMapper.selectArticleImgList(bookIdx);
		article.setImgList(imgList);
		
		return article;
		
	}

	// 댓글 리스트
	@Override
	public List<CommentDto> selectCommentList(int bookIdx) throws Exception {
		
		List<CommentDto> commentList = articleMapper.selectCommentList(bookIdx);
		
		for(CommentDto name : commentList){
			name.setUserName(articleMapper.selectUserInfo(name.getUserIdx()));
		}

		return commentList;
	}
	
	// 댓글 쓰기
	@Override
	public void insertComment(CommentDto commentDto) throws Exception {
		articleMapper.insertComment(commentDto);
		
	}
	// 댓글 삭제
	public void deleteComment(CommentDto commentDto) throws Exception {
		articleMapper.deleteComment(commentDto);
	}

	// 글쓰기
	@Override
	public void insertArticle(ArticleDto articleDto, MultipartHttpServletRequest multiFiles) throws Exception {
		
		articleMapper.insertArticle(articleDto);
		
		List<ImgDto> list = fileUtils.parseFileInfo(articleDto.getBookIdx(), multiFiles);
		
		if(CollectionUtils.isEmpty(list) == false) {
			articleMapper.insertArticleFileList(list);
		}
	}

	// 도서 대 분류
	@Override
	public List<TagDto> tagMainCategory() throws Exception {
	
		return articleMapper.tagMainCategory();
	}

	// 도서 세부 분류
	@Override
	public List<TagDto> tagDetailCategory(int tagIdx) throws Exception {
		
		int tagIdx2 = tagIdx + 100;
		return articleMapper.tagDetailCategory(tagIdx, tagIdx2);
	}

	// 글 수정
	@Override
	public void editArticle(ArticleDto articleDto, MultipartHttpServletRequest multiFiles) throws Exception {
		
		articleMapper.editArticle(articleDto);
		articleMapper.editImgArticle(articleDto.getBookIdx());
		
		List<ImgDto> list = fileUtils.parseFileInfo(articleDto.getBookIdx(), multiFiles);
		
		if(CollectionUtils.isEmpty(list) == false) {
			articleMapper.insertArticleFileList(list);
		}
		
	}

	// 글 비활성화
	@Override
	public void deleteArticle(int bookIdx) throws Exception {
		articleMapper.deleteArticle(bookIdx);
		articleMapper.editImgArticle(bookIdx);
		articleMapper.deleteComment(bookIdx);
	}

	// 추천
	@Override
	public String recommendArticle(RecommendDto recommend) throws Exception {
		if(articleMapper.selectRecommend(recommend) != 0) {
			return "F";
		} else {
			articleMapper.insertRecommend(recommend);
			articleMapper.updateRecommend(recommend);
			return "S";
		}
	}

	// 판매완료
	@Override
	public void articleSoldYn(int bookIdx) throws Exception {
		articleMapper.articleSoldYn(bookIdx);
	}

	// 메인페이지
	@Override
	public List<ArticleDto> selectLatestPost() throws Exception {
		return articleMapper.selectLatestPost();
	}
	@Override
	public List<ArticleDto> selectHitPost() throws Exception {
		return articleMapper.selectHitPost();
	}
	@Override
	public List<ArticleDto> selectFollowPost() throws Exception {
		return articleMapper.selectFollowPost();
	}

	// 메인 주제별 리스트 페이지
	@Override
	public List<ArticleDto> selectLatestPosts() throws Exception {
		return articleMapper.selectLatestPosts();
	}
	@Override
	public List<ArticleDto> selectHitPosts() throws Exception {
		return articleMapper.selectHitPosts();
	}
	@Override
	public List<ArticleDto> selectFollowPosts() throws Exception {
		return articleMapper.selectFollowPosts();
	}
}
