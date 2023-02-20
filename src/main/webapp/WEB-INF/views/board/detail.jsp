<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	<button id="btn-update" class="btn btn-warning">수정</button>
	<c:if test="${board.user.id == principal.user.id}">
		<button id="btn-delete" class="btn btn-danger">삭제</button>
	</c:if>
	<br /><br />
	<div>
		글 번호 : <span id="id"><i>${board.id} </i></span>
		작성자 : <span><i>${board.user.username} </i></span>
	</div>
	<br />
	<div>
		<h3>${board.title}</h3>
	</div>
	<hr />

	<div>
		<div>${board.content}</div>
	</div>
	<hr />
</div>

<!-- 수정/삭제할 때 자바스크립트 이용하니까 자바스크립트는 넣어주기. -->
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>