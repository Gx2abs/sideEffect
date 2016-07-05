<%@ page language="java"   pageEncoding="UTF-8"%>
<%@ include file="/view/config/config.jsp" %>

<head>
<meta http-equiv="content-type" content="text/html; charset=euc-kr">
    <link rel="stylesheet" type="text/css" href="view/jsp/common/tree/DynamicTree.css" />
    <script type="text/javascript" src="view/jsp/common/tree/ie5.js"></script>
    <script type="text/javascript" src="view/jsp/common/tree/DynamicTree.js"></script>
    <style type="text/css">
    p { font-family: georgia, sans-serif; font-size: 11px; }
    </style>
</head>
<body>
편집하기 엄청 쉽습니다 .div class로 묶어주기만 하면 됩니다.
    <div class="DynamicTree">
        <div class="top">트리메뉴</div>
        <div class="wrap" id="tree">
            <div class="folder">폴더1
                <div class="folder">폴더
                    <div class="folder">폴더
                        <div class="doc"><a href="#" title="Node 1.1.1.1" target="_self">자바스크립트</a></div>
      <div class="doc"><a href="#" title="Node 1.1.1.1" target="_self">자바스크립트</a></div>
                    </div>
                </div>
                <div class="doc"><a href="#">자바스크립트</a></div>
            </div>
            <div class="doc"><a href="#">동영상 편집</a></div>
            <div class="doc"><a href="#">계정관리</a></div>
            <div class="doc"><a href="#">자료실</a></div>
            <div class="folder">폴더
                <div class="doc"><a href="#">게시판</a></div>
                <div class="doc"><a href="#">기타</a></div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
    var tree = new DynamicTree("tree");
    tree.init();
    </script>
</body>
</html>
