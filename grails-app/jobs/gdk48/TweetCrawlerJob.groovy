package gdk48

import twitter4j.*
import org.apache.commons.logging.LogFactory

class TweetCrawlerJob {

    static final log = LogFactory.getLog(TweetCrawlerJob)

    static final int MAX_TRY = 5
    static final String INCLUDE_URL = /https?:\/\/\S+/
    static final String GIST_URL = /https:\/\/gist\.github\.com\/(\d+)/

    def concurrent = false

    static triggers = {
        simple name: 'TweetCrawlerTrigger', startDelay: 10000, repeatInterval: 60000
    }

    def execute() {
        log.info 'start.'
        def twitter = new TwitterFactory().instance
        twitter.search(new Query('#gdk48')).tweets.each { parse it }
        log.info 'end.'
    }

    private void parse(Tweet tweet) {
        getURLs(tweet.text).each { url ->
            def gistNo = getGistNo(url)
            if (gistNo && !Gist.findByGistNo(gistNo)) {
                new Gist(gistNo:gistNo).save()
            }
        }
    }

    private List<String> getURLs(String text) {
        (text =~ INCLUDE_URL).collect { it }
    }

    private Long getGistNo(String url, tryCount = 0) {
        if (tryCount >= MAX_TRY) {
            return null
        }
        def m = url =~ GIST_URL
        if (m) {
            return m[0][1].toLong()
        } else {
            def conn = new URL(url).openConnection()
            conn.followRedirects = false
            def location = conn.getHeaderField('Location')
            return location ? getGistNo(location, ++tryCount) : null
        }
    }
}
