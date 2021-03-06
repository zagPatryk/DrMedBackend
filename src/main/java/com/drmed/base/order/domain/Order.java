package com.drmed.base.order.domain;

import com.drmed.base.additional.statuses.ResultStatus;
import com.drmed.base.orderedTest.domain.OrderedTest;
import com.drmed.base.visit.domain.Visit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Order {
    private Long id;
    private String code;
    private Visit visit;
    private List<OrderedTest> orderedTests = new ArrayList<>();
    private ResultStatus orderResultStatus;

    public Order(Long id, String code, Visit visit, List<OrderedTest> orderedTests, ResultStatus orderResultStatus) {
        this.id = id;
        this.code = code;
        this.visit = visit;
        this.orderedTests = orderedTests;
        this.orderResultStatus = orderResultStatus;
    }

    public static class OrderBuilder {
        private Long id;
        private String code;
        private Visit visit;
        private List<OrderedTest> orderedTests = new ArrayList<>();
        private ResultStatus orderResultStatus;

        public OrderBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public OrderBuilder setCode(String code) {
            this.code = code;
            return this;
        }

        public OrderBuilder setVisit(Visit visit) {
            this.visit = visit;
            return this;
        }

        public OrderBuilder setOrderedTests(List<OrderedTest> orderedTests) {
            this.orderedTests = orderedTests;
            return this;
        }

        public OrderBuilder setOrderResultStatus(ResultStatus orderResultStatus) {
            this.orderResultStatus = orderResultStatus;
            return this;
        }

        public Order build() {
            return new Order(id, code, visit, orderedTests, orderResultStatus);
        }
    }
}
