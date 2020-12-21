package com.drmed.api.apimedic.data.diagnosis.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class IssueResponse {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("ProfName")
    private String professionalName;
    @JsonProperty("Icd")
    private String icd;
    @JsonProperty("IcdName")
    private String icdName;
}