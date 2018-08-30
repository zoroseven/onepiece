<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>文件上传</title>
</head>
<body>
<form method="post" action="/doUpload" enctype="multipart/form-data">
	单个文件上传（最大100Kb）：<input type="file" name="file" />
	<input type="submit" value="提交上传"/>
</form>

<form method="post" action="/doUploads" enctype="multipart/form-data">
	第1个文件：<input type="file" name="file" /><br/>
	第2个文件：<input type="file" name="file" /><br/>
	<input type="submit" value="提交上传"/>
</form>
</body>
</html>