package com.feng.service;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OceanFreightView {

    @JsonView({View.Platform.class, View.Client.class})
    private String currency;
    private String remarks;

    @JsonView({View.Platform.class, View.Client.class})
    private List<Price> prices;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Price {

        @JsonView(View.Platform.class)
        private Double rangeFromExclusive;
        @JsonView(View.Platform.class)
        private Double rangeToInclusive;

        @JsonView(View.Platform.class)
        private Double CbmPrice;
        @JsonView(View.Platform.class)
        private Double TonPrice;

        @JsonView(View.Client.class)
        public Double getCbmPrice() {
            return CbmPrice;
        }

        @JsonView(View.Client.class)
        public double getTonPrice() {
            return TonPrice;
        }
    }
}
