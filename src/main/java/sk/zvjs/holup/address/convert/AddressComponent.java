package sk.zvjs.holup.address.convert;

import com.fasterxml.jackson.annotation.*;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressComponent {
    @lombok.Getter(onMethod_ = {@JsonProperty("long_name")})
    @lombok.Setter(onMethod_ = {@JsonProperty("long_name")})
    private String longName;
    @lombok.Getter(onMethod_ = {@JsonProperty("short_name")})
    @lombok.Setter(onMethod_ = {@JsonProperty("short_name")})
    private String shortName;
    @lombok.Getter(onMethod_ = {@JsonProperty("types")})
    @lombok.Setter(onMethod_ = {@JsonProperty("types")})
    private String[] types;
}
