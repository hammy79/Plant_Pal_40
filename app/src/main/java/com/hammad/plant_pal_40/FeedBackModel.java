package com.hammad.plant_pal_40;


public class FeedBackModel {
    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FeedBackModel(String feedbackType, String message) {
        this.feedbackType = feedbackType;
        this.message = message;
    }

    private String feedbackType;
    private String message;
}
