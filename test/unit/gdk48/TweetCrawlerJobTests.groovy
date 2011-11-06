package gdk48

import grails.test.*
import org.junit.*
import twitter4j.*

class TweetCrawlerJobTests extends GrailsUnitTestCase {

    @Ignore('ちょっと時間がかかる')
    void testGetGistNo() {
        def job = new TweetCrawlerJob()
        assert job.getGistNo('https://gist.github.com/1261979') == 1261979
        assert job.getGistNo('https://gist.github.com/') == null
        assert job.getGistNo('https://github.com/1261979') == null

        // https://gist.github.com/1197737
        assert job.getGistNo('http://t.co/SRGK93o') == 1197737
    }

    void testGetURLs() {
        def job = new TweetCrawlerJob()
        assert job.getURLs('http://example.com') == ['http://example.com']
        assert job.getURLs('https://example.com') == ['https://example.com']
        assert job.getURLs('URLはこちら http://example.com クリック') == ['http://example.com']
        assert job.getURLs('URLはこれ http://example.com とこれ　http://example.org #gdk48') == ['http://example.com', 'http://example.org']
        assert job.getURLs('''URLはこちら
            http://example.com
            クリック''') == ['http://example.com']
        assert job.getURLs('合宿なう') == []
    }

    void testParse() {
        mockDomain(Gist)
        def job = new TweetCrawlerJob()
        def tweet = [getText:{'https://gist.github.com/1261979'}, getCreatedAt:{Date.parse('yyyyMMdd', '20111105')}] as Tweet
        job.parse(tweet)

        assert Gist.count() == 1
        def gist = Gist.list()[0]
        assert gist
        assert gist.gistNo == 1261979
    }

    void testParse_同じツイートを受信しても登録するのは1件() {
        mockDomain(Gist)
        def job = new TweetCrawlerJob()
        def tweet = [getText:{'https://gist.github.com/1261979'}, getCreatedAt:{Date.parse('yyyyMMdd', '20111105')}] as Tweet
        job.parse(tweet)
        job.parse(tweet)

        assert Gist.count() == 1
    }

    void testParse_同じGistがあっても登録されるのは1件() {
        mockDomain(Gist)
        def job = new TweetCrawlerJob()
        def tweet = [getText:{'https://gist.github.com/1261979 https://gist.github.com/1261979'}, getCreatedAt:{Date.parse('yyyyMMdd', '20111105')}] as Tweet
        job.parse(tweet)

        assert Gist.count() == 1
    }
}
