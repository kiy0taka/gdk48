<%@ page import="gdk48.*" %>
<html>
<head>
<meta name="layout" content="main" />
<g:javascript library='jquery' plugin='jquery' />
<title>GDK48総選挙</title>
<blueprint:resources/>
<style>
body {
  background-color: #FAEBD7;
}
.gist {
  margin-bottom: 30px;
}
.gist span.description {
  padding-left: 5px;
  font-size: 18px;
}
</style>
<script type="text/javascript">
function updateCount(button, addVal) {
  var votecount = button.siblings('.votecount')
  votecount.text(parseInt(votecount.text(), 10) + addVal)
}
function toggle(gistNo, button) {
  var btn = $(button)
  if (btn.val() == '投票') {
    $.post("${createLink(controller:'vote', action:'ajaxVote')}", {gistNo:gistNo}).success(function() {
      btn.val('取消')
      updateCount(btn, 1)
    })
  } else {
    $.post("${createLink(controller:'vote', action:'ajaxUnvote')}", {gistNo:gistNo}).success(function() {
      btn.val('投票')
      updateCount(btn, -1)
    })
  }
}
$(function() {
  $('.description').each(function() {
    var dest = $(this)
    var gistNo = $(this).attr('id').match(/desc-(\d+)/)[1]
    $.getJSON('https://api.github.com/gists/'+gistNo+'?callback=?', function(resp) {
      var description = resp.data.description || '<No description>'
      if (resp.data.user) {
        description += ' (by '+resp.data.user.login+')'
      }
      dest.text(description)
    })
  })
})
</script>
</head>
<body>
<div class="container">
  <h1 class="span-26 last">GDK48 総選挙</h1>
  <div class="paginateButtons">
    <g:paginate controller="gist" action="list" total="${Gist.count()}" max="5" />
  </div>
  <div class="span-26 last" style="background-color: #FFB6C1; padding: 30px;"> 
      <g:render template="gist" collection="${gists}" var="gist"/>
  </div>
  <div class="paginateButtons">
    <g:paginate controller="gist" action="list" total="${Gist.count()}" max="5" />
  </div>
</div>
</body>
</html>
