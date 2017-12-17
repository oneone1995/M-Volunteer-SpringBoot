package com.github.oneone1995.mvolunteer.model;

/**
 * Created by wangl on 2017/2/26.
 * 群组创建对应的model
 * @deprecated 改用环信即时通讯云
 * @see com.github.oneone1995.mvolunteer.model.EasemobIMChatGroupModel
 */
@Deprecated
public class IMTribeModel {

    /**
     * openim_tribe_create_response : {"tribe_info":{"check_mode":0,"name":"环保助力中国梦","notice":"欢迎加入环保助力中国梦志愿活动","recv_flag":0,"tribe_id":2084012145,"tribe_type":0},"request_id":"10ewv3wiibkwv"}
     */

    private OpenimTribeCreateResponseBean openim_tribe_create_response;

    public OpenimTribeCreateResponseBean getOpenim_tribe_create_response() {
        return openim_tribe_create_response;
    }

    public void setOpenim_tribe_create_response(OpenimTribeCreateResponseBean openim_tribe_create_response) {
        this.openim_tribe_create_response = openim_tribe_create_response;
    }

    public static class OpenimTribeCreateResponseBean {
        /**
         * tribe_info : {"check_mode":0,"name":"环保助力中国梦","notice":"欢迎加入环保助力中国梦志愿活动","recv_flag":0,"tribe_id":2084012145,"tribe_type":0}
         * request_id : 10ewv3wiibkwv
         */

        private TribeInfoBean tribe_info;
        private String request_id;

        public TribeInfoBean getTribe_info() {
            return tribe_info;
        }

        public void setTribe_info(TribeInfoBean tribe_info) {
            this.tribe_info = tribe_info;
        }

        public String getRequest_id() {
            return request_id;
        }

        public void setRequest_id(String request_id) {
            this.request_id = request_id;
        }

        public static class TribeInfoBean {
            /**
             * check_mode : 0
             * name : 环保助力中国梦
             * notice : 欢迎加入环保助力中国梦志愿活动
             * recv_flag : 0
             * tribe_id : 2084012145
             * tribe_type : 0
             */

            private int check_mode;
            private String name;
            private String notice;
            private int recv_flag;
            private int tribe_id;
            private int tribe_type;

            public int getCheck_mode() {
                return check_mode;
            }

            public void setCheck_mode(int check_mode) {
                this.check_mode = check_mode;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }

            public int getRecv_flag() {
                return recv_flag;
            }

            public void setRecv_flag(int recv_flag) {
                this.recv_flag = recv_flag;
            }

            public int getTribe_id() {
                return tribe_id;
            }

            public void setTribe_id(int tribe_id) {
                this.tribe_id = tribe_id;
            }

            public int getTribe_type() {
                return tribe_type;
            }

            public void setTribe_type(int tribe_type) {
                this.tribe_type = tribe_type;
            }
        }
    }
}
