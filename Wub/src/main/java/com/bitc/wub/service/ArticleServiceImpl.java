package com.bitc.wub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bitc.wub.common.FileUtils;
import com.bitc.wub.dto.ArticleDto;
import com.bitc.wub.dto.CommentDto;
import com.bitc.wub.dto.ImgDto;
import com.bitc.wub.dto.TagDto;
import com.bitc.wub.mapper.ArticleMapper;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private FileUtils fileUtils;

	// 상세 글 보기
	@Override
	public ArticleDto selectArticleDetail(int BookIdx) throws Exception {
		
		// 내부에 userIdx 존재
		ArticleDto article = articleMapper.selectArticleDetail(BookIdx);
		
		// 다른 테이블 참조
		//userDto user = articleMapper.selectUserLocal(article.getUserIdx());
		//article.setUserName(user.getUserName);
		//article.setUserLocal(user.getUserLocal);
		
		List<ImgDto> imgList = articleMapper.selectArticleImgList(BookIdx);
		article.setImgList(imgList);
		
		return article;
		
	}

	// 댓글 리스트
	@Override
	public List<CommentDto> selectCommentList(int BookIdx) throws Exception {
		
		return articleMapper.selectCommentList(BookIdx);
	}
	
	// 댓글 쓰기
	@Override
	public void insertComment(CommentDto commentDto) throws Exception {
		articleMapper.insertComment(commentDto);
		
	}
	
	// 조회수 상승
	@Override
	public void countHitCnt(int BookIdx) throws Exception {
		articleMapper.countHitCnt(BookIdx);
		
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
	public List<TagDto> tagMainCategori() throws Exception {
	
		return articleMapper.tagMainCategori();
	}

	// 도서 세부 분류
	@Override
	public List<TagDto> tagDetailCategori(int tagIdx) throws Exception {
		
		int tagIdx2 = tagIdx + 100;
		return articleMapper.tagDetailCategori(tagIdx, tagIdx2);
	}

	

}
