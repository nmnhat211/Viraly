package com.example.viralyapplication.ui.event;

public class BaseEvent {
        private boolean status;
        private String errorMessage;
        private Object data;
        private String filterName;

        public boolean getStatus() {
            return status;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public Object getData() {
            return data;
        }

        public String getFilterName() {
            return filterName;
        }

        public void setFilterName(String filterName) {
            this.filterName = filterName;
        }

        public BaseEvent(boolean status, String errorMessage, Object data){
            this.status = status;
            this.errorMessage = errorMessage;
            this.data = data;
        }
}
