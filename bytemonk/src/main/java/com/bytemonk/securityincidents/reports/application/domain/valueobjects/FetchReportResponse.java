package com.bytemonk.securityincidents.reports.application.domain.valueobjects;

import java.util.List;

public record FetchReportResponse(List<CreatedReportResponse> reports) {
}
