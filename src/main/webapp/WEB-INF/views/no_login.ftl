<script type="text/javascript">
    alert("${msg}");
    window.parent.location.href="${ctx}/index";
</script>
<#--
<html>
<head>
    <#include "common.ftl" >
    <title></title>
    <script type="text/javascript">
        $( function () {
            alert("${errorMsg!}");
            if ("${uri}" == "/main") {
                window.location.href = "${ctx}/index";
            }
            else {
                window.parent.location.href = "${ctx}/index";
            }
        })
    </script>
</head>
<body>
</body>
</html>-->
