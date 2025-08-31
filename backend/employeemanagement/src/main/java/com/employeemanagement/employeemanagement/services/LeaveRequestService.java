package com.employeemanagement.employeemanagement.services;

import com.employeemanagement.employeemanagement.dto.LeaveRequestDto;
import java.util.List;

public interface LeaveRequestService {
    List<LeaveRequestDto> getAllLeaveRequests();
    LeaveRequestDto getLeaveRequestById(Integer id);
    LeaveRequestDto createLeaveRequest(LeaveRequestDto leaveRequestDto);
    LeaveRequestDto updateLeaveRequest(Integer id, LeaveRequestDto leaveRequestDto);
    void deleteLeaveRequest(Integer id);

    LeaveRequestDto approveLeave(Integer id);
    LeaveRequestDto rejectLeave(Integer id);
    List<LeaveRequestDto> getLeaveRequestsByUser(Integer userId);
}
