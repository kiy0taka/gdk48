<div class="gist">
    <div>
      <sec:ifLoggedIn>
      <g:if test="${!gist.voted}">
        <input type="button" value="投票" onclick="toggle(${gist.gistNo}, this)"/>
      </g:if>
      <g:if test="${gist.voted}">
        <input type="button" value="取消" onclick="toggle(${gist.gistNo}, this)"/>
      </g:if>
      (<span class="votecount">${gist.votedCount}</span>)
      </sec:ifLoggedIn>
      <span id="desc-${gist.gistNo}" class="description"></span>
    </div>
    <div>
    <script src="https://gist.github.com/${gist.gistNo}.js"></script>
    </div>
</div>
