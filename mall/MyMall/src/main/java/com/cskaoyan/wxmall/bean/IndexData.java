package com.cskaoyan.wxmall.bean;

import com.cskaoyan.mall.bean.generator.Keyword;
import com.cskaoyan.mall.bean.generator.SearchHistory;

import java.util.List;

public class IndexData {

    /**
     * errno : 0
     * data : {"defaultKeyword":{"id":6,"keyword":"520元礼包抢先领","url":"","isHot":true,"isDefault":true,"sortOrder":1,"addTime":"2018-01-31 19:00:00","updateTime":"2018-01-31 19:00:00","deleted":false},"hotKeywordList":[{"id":6,"keyword":"520元礼包抢先领","url":"","isHot":true,"isDefault":true,"sortOrder":1,"addTime":"2018-01-31 19:00:00","updateTime":"2018-01-31 19:00:00","deleted":false},{"id":14,"keyword":"13test","url":"ref","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-09-30 10:35:49","updateTime":"2019-09-30 12:04:21","deleted":false},{"id":17,"keyword":"405","url":"没有链接","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-10-01 05:45:26","updateTime":"2019-10-03 05:15:54","deleted":false},{"id":18,"keyword":"晓梦人宗，天地失色","url":"庄周化蝶","isHot":true,"isDefault":false,"sortOrder":100,"addTime":"2019-10-01 09:39:03","updateTime":"2019-10-01 09:39:03","deleted":false},{"id":19,"keyword":"诸子百家-阴阳家","url":"星魂与大司命，焱妃","isHot":true,"isDefault":false,"sortOrder":100,"addTime":"2019-10-01 09:46:42","updateTime":"2019-10-01 10:23:43","deleted":false},{"id":20,"keyword":"十年磨一剑","url":"霜刃未曾试","isHot":true,"isDefault":false,"sortOrder":100,"addTime":"2019-10-01 09:51:47","updateTime":"2019-10-01 09:51:47","deleted":false},{"id":26,"keyword":"快说你是猪吗？","url":"快说","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-10-01 11:14:51","updateTime":"2019-10-01 11:14:51","deleted":false},{"id":27,"keyword":"蓝胖","url":"是蓝胖呀","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-10-01 11:45:49","updateTime":"2019-10-01 11:45:49","deleted":false},{"id":28,"keyword":"小可爱","url":"啾咪","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-10-01 11:54:11","updateTime":"2019-10-01 11:54:47","deleted":false},{"id":29,"keyword":"猪猪女孩","url":"嘻嘻","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-10-01 11:55:11","updateTime":"2019-10-01 11:55:11","deleted":false},{"id":31,"keyword":"郑爽","url":"正版","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-10-01 11:56:06","updateTime":"2019-10-01 11:56:06","deleted":false},{"id":32,"keyword":"大幂幂","url":"少女呢","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-10-01 11:56:28","updateTime":"2019-10-01 21:27:20","deleted":false},{"id":33,"keyword":"迪丽冷巴","url":"呼啦呼啦","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-10-01 11:56:53","updateTime":"2019-10-01 11:56:53","deleted":false},{"id":38,"keyword":"是蓝胖吗","url":"是的","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-10-02 02:28:57","updateTime":"2019-10-02 02:28:57","deleted":false},{"id":39,"keyword":"是蓝胖吗","url":"你猜","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-10-02 02:29:20","updateTime":"2019-11-15 23:22:28","deleted":false},{"id":44,"keyword":"你是蓝胖？","url":"222","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-10-02 05:52:29","updateTime":"2019-10-02 05:52:29","deleted":false},{"id":48,"keyword":"百度","url":"1","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-11-15 23:32:51","updateTime":"2019-11-15 23:40:23","deleted":false},{"id":49,"keyword":"xiang","url":"aaa","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-11-16 03:28:56","updateTime":"2019-11-16 03:28:56","deleted":false},{"id":51,"keyword":"阿达","url":"奥德赛","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-11-16 07:53:29","updateTime":"2019-11-16 07:53:29","deleted":false},{"id":53,"keyword":"阿萨德","url":"阿萨德","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-11-16 08:31:05","updateTime":"2019-11-16 08:31:05","deleted":false},{"id":54,"keyword":"他的","url":"123","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-11-16 23:48:58","updateTime":"2019-11-16 23:48:58","deleted":false},{"id":57,"keyword":"阿萨德","url":"阿萨德a","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-11-17 04:00:46","updateTime":"2019-11-17 04:00:46","deleted":false},{"id":59,"keyword":"123","url":"1234","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-11-17 07:03:23","updateTime":"2019-11-17 07:51:24","deleted":false}],"historyKeywordList":[{"keyword":"蓝胖"},{"keyword":"123"},{"keyword":"520元礼包抢先领"},{"keyword":"hah"},{"keyword":"十年磨一剑"},{"keyword":"阿瓦提"}]}
     * errmsg : 成功
     */

    private int errno;
    private DataBean data;
    private String errmsg;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public static class DataBean {
        /**
         * defaultKeyword : {"id":6,"keyword":"520元礼包抢先领","url":"","isHot":true,"isDefault":true,"sortOrder":1,"addTime":"2018-01-31 19:00:00","updateTime":"2018-01-31 19:00:00","deleted":false}
         * hotKeywordList : [{"id":6,"keyword":"520元礼包抢先领","url":"","isHot":true,"isDefault":true,"sortOrder":1,"addTime":"2018-01-31 19:00:00","updateTime":"2018-01-31 19:00:00","deleted":false},{"id":14,"keyword":"13test","url":"ref","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-09-30 10:35:49","updateTime":"2019-09-30 12:04:21","deleted":false},{"id":17,"keyword":"405","url":"没有链接","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-10-01 05:45:26","updateTime":"2019-10-03 05:15:54","deleted":false},{"id":18,"keyword":"晓梦人宗，天地失色","url":"庄周化蝶","isHot":true,"isDefault":false,"sortOrder":100,"addTime":"2019-10-01 09:39:03","updateTime":"2019-10-01 09:39:03","deleted":false},{"id":19,"keyword":"诸子百家-阴阳家","url":"星魂与大司命，焱妃","isHot":true,"isDefault":false,"sortOrder":100,"addTime":"2019-10-01 09:46:42","updateTime":"2019-10-01 10:23:43","deleted":false},{"id":20,"keyword":"十年磨一剑","url":"霜刃未曾试","isHot":true,"isDefault":false,"sortOrder":100,"addTime":"2019-10-01 09:51:47","updateTime":"2019-10-01 09:51:47","deleted":false},{"id":26,"keyword":"快说你是猪吗？","url":"快说","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-10-01 11:14:51","updateTime":"2019-10-01 11:14:51","deleted":false},{"id":27,"keyword":"蓝胖","url":"是蓝胖呀","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-10-01 11:45:49","updateTime":"2019-10-01 11:45:49","deleted":false},{"id":28,"keyword":"小可爱","url":"啾咪","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-10-01 11:54:11","updateTime":"2019-10-01 11:54:47","deleted":false},{"id":29,"keyword":"猪猪女孩","url":"嘻嘻","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-10-01 11:55:11","updateTime":"2019-10-01 11:55:11","deleted":false},{"id":31,"keyword":"郑爽","url":"正版","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-10-01 11:56:06","updateTime":"2019-10-01 11:56:06","deleted":false},{"id":32,"keyword":"大幂幂","url":"少女呢","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-10-01 11:56:28","updateTime":"2019-10-01 21:27:20","deleted":false},{"id":33,"keyword":"迪丽冷巴","url":"呼啦呼啦","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-10-01 11:56:53","updateTime":"2019-10-01 11:56:53","deleted":false},{"id":38,"keyword":"是蓝胖吗","url":"是的","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-10-02 02:28:57","updateTime":"2019-10-02 02:28:57","deleted":false},{"id":39,"keyword":"是蓝胖吗","url":"你猜","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-10-02 02:29:20","updateTime":"2019-11-15 23:22:28","deleted":false},{"id":44,"keyword":"你是蓝胖？","url":"222","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-10-02 05:52:29","updateTime":"2019-10-02 05:52:29","deleted":false},{"id":48,"keyword":"百度","url":"1","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-11-15 23:32:51","updateTime":"2019-11-15 23:40:23","deleted":false},{"id":49,"keyword":"xiang","url":"aaa","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-11-16 03:28:56","updateTime":"2019-11-16 03:28:56","deleted":false},{"id":51,"keyword":"阿达","url":"奥德赛","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-11-16 07:53:29","updateTime":"2019-11-16 07:53:29","deleted":false},{"id":53,"keyword":"阿萨德","url":"阿萨德","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-11-16 08:31:05","updateTime":"2019-11-16 08:31:05","deleted":false},{"id":54,"keyword":"他的","url":"123","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-11-16 23:48:58","updateTime":"2019-11-16 23:48:58","deleted":false},{"id":57,"keyword":"阿萨德","url":"阿萨德a","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-11-17 04:00:46","updateTime":"2019-11-17 04:00:46","deleted":false},{"id":59,"keyword":"123","url":"1234","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2019-11-17 07:03:23","updateTime":"2019-11-17 07:51:24","deleted":false}]
         * historyKeywordList : [{"keyword":"蓝胖"},{"keyword":"123"},{"keyword":"520元礼包抢先领"},{"keyword":"hah"},{"keyword":"十年磨一剑"},{"keyword":"阿瓦提"}]
         */

        private Keyword defaultKeyword;
        private List<Keyword> hotKeywordList;
        private List<SearchHistory> historyKeywordList;

        public Keyword getDefaultKeyword() {
            return defaultKeyword;
        }

        public void setDefaultKeyword(Keyword defaultKeyword) {
            this.defaultKeyword = defaultKeyword;
        }

        public List<Keyword> getHotKeywordList() {
            return hotKeywordList;
        }

        public void setHotKeywordList(List<Keyword> hotKeywordList) {
            this.hotKeywordList = hotKeywordList;
        }

        public List<SearchHistory> getHistoryKeywordList() {
            return historyKeywordList;
        }

        public void setHistoryKeywordList(List<SearchHistory> historyKeywordList) {
            this.historyKeywordList = historyKeywordList;
        }

    }
}
