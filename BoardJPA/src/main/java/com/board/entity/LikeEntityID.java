package com.board.entity;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode // 변수값들을 serialize화 하기 위해서 필요한 작업을 해 줌
public class LikeEntityID {
	private Long seqno;
	private String email;
}
