<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <link rel="stylesheet" href="${resource(dir:'css',file:'twitter-auth.css')}" />
        <g:layoutHead />
        <g:javascript library="application" />
    </head>
    <body>
        <sec:ifNotLoggedIn>
        <twitterAuth:button/>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
        Logged in as <sec:loggedInUserInfo field="username"/>
        </sec:ifLoggedIn>
        <g:layoutBody />
    </body>
</html>