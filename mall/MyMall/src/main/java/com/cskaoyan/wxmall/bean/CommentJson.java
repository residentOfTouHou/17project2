package com.cskaoyan.wxmall.bean;

import java.util.Date;
import java.util.List;

public class CommentJson {
    /**
     * errno : 0
     * data : {"data":[{"userInfo":{"nickName":"dr lan","avatarUrl":""},"addTime":"2019-10-06 10:58:16","picList":["http://192.168.2.100:8081/wx/storage/fetch/n86mzy3ngaxralyqpq9n.jpg","http://192.168.2.100:8081/wx/storage/fetch/7hsiizmt2ou4kqpsygwh.jpg"],"content":"ljq test"},{"userInfo":{"nickName":"dr lan","avatarUrl":""},"addTime":"2019-10-06 07:59:56","picList":["http://192.168.2.100:8081/wx/storage/fetch/3157vn12vvsvpfobtsoq.jpg"],"content":"ljq"},{"userInfo":{"nickName":"dr lan","avatarUrl":""},"addTime":"2019-10-04 12:14:08","picList":["http://192.168.2.100:8081/wx/storage/fetch/9apc2d5cg9eam1sic74r.jpg"],"content":"4055555奥术大师多"},{"userInfo":{"nickName":"dr lan","avatarUrl":""},"addTime":"2019-08-22 20:45:21","picList":[],"content":"we"},{"userInfo":{"nickName":"dr lan","avatarUrl":""},"addTime":"2019-08-22 07:45:25","picList":[],"content":"111"}],"count":6,"currentPage":1}
     * errmsg : 成功
     */

    private int errno;
    private DataBeanX data;
    private String errmsg;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public static class DataBeanX {
        /**
         * data : [{"userInfo":{"nickName":"dr lan","avatarUrl":""},"addTime":"2019-10-06 10:58:16","picList":["http://192.168.2.100:8081/wx/storage/fetch/n86mzy3ngaxralyqpq9n.jpg","http://192.168.2.100:8081/wx/storage/fetch/7hsiizmt2ou4kqpsygwh.jpg"],"content":"ljq test"},{"userInfo":{"nickName":"dr lan","avatarUrl":""},"addTime":"2019-10-06 07:59:56","picList":["http://192.168.2.100:8081/wx/storage/fetch/3157vn12vvsvpfobtsoq.jpg"],"content":"ljq"},{"userInfo":{"nickName":"dr lan","avatarUrl":""},"addTime":"2019-10-04 12:14:08","picList":["http://192.168.2.100:8081/wx/storage/fetch/9apc2d5cg9eam1sic74r.jpg"],"content":"4055555奥术大师多"},{"userInfo":{"nickName":"dr lan","avatarUrl":""},"addTime":"2019-08-22 20:45:21","picList":[],"content":"we"},{"userInfo":{"nickName":"dr lan","avatarUrl":""},"addTime":"2019-08-22 07:45:25","picList":[],"content":"111"}]
         * count : 6
         * currentPage : 1
         */

        private int count;
        private int currentPage;
        private List<DataBean> data;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * userInfo : {"nickName":"dr lan","avatarUrl":""}
             * addTime : 2019-10-06 10:58:16
             * picList : ["http://192.168.2.100:8081/wx/storage/fetch/n86mzy3ngaxralyqpq9n.jpg","http://192.168.2.100:8081/wx/storage/fetch/7hsiizmt2ou4kqpsygwh.jpg"]
             * content : ljq test
             */

            private UserInfoBean userInfo;
            private Date addTime;
            private String content;
            private String[] picList;

            public UserInfoBean getUserInfo() {
                return userInfo;
            }

            public void setUserInfo(UserInfoBean userInfo) {
                this.userInfo = userInfo;
            }

            public Date getAddTime() {
                return addTime;
            }

            public void setAddTime(Date addTime) {
                this.addTime = addTime;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String[] getPicList() {
                return picList;
            }

            public void setPicList(String[] picList) {
                this.picList = picList;
            }

            public static class UserInfoBean {
                /**
                 * nickName : dr lan
                 * avatarUrl :
                 */

                private String nickName;
                private String avatarUrl;

                public String getNickName() {
                    return nickName;
                }

                public void setNickName(String nickName) {
                    this.nickName = nickName;
                }

                public String getAvatarUrl() {
                    return avatarUrl;
                }

                public void setAvatarUrl(String avatarUrl) {
                    this.avatarUrl = avatarUrl;
                }
            }
        }
    }
}
