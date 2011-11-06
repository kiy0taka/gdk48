<%@ page import="gdk48.*" %>
<html>
<head>
<link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
<g:javascript library='jquery' plugin='jquery' />
<title>GDK48総選挙</title>
<script type="text/javascript">
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
  <div class="body">
    <h1>GDK48総選挙　開票結果</h1>
    <div class="list">
    <table>
    <tr>
      <th>Gist</th>
      <th>獲得票数</th>
    </tr>
    <g:each var="voteList" in="${votes}">
    <tr>
      <td><a target="_blank" href="https://gist.github.com/${voteList[0].gist.gistNo}">${voteList[0].gist.gistNo}</a></td>
      <td>${voteList.size()}</td>
    </tr>
    </g:each>
    </table>
    </div>
  </div>
</body>
</html>
