package com.employeemanagement.employeemanagement.services;

import com.employeemanagement.employeemanagement.dto.FeedbackDto;
import java.util.List;

public interface FeedbackService {
    List<FeedbackDto> getAllFeedbacks();
    FeedbackDto getFeedbackById(Integer id);
    FeedbackDto createFeedback(FeedbackDto feedbackDto);
    void deleteFeedback(Integer id);

    List<FeedbackDto> getFeedbacksByUser(Integer userId);
}
