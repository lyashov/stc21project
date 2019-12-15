package ru.innopolis.stc21.med.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.stc21.med.service.GeoIP;
import ru.innopolis.stc21.med.service.GeoIPService;

@Controller
public class GeoIPController {

    public GeoIPController() {
    }

    @GetMapping("/geoip")
    public String geoGetIp() {
        return "geoip";
    }

    @RequestMapping(value = "/geoip")
    public String geoIpLocation(
            @RequestParam(value = "ipAddress", required = true) String ipAddress
    ) throws Exception {

        GeoIPService locationService
                = new GeoIPService();

        locationService.getLocation(ipAddress);
        return "redirect:/hospitallocation";
    }

    @GetMapping("/hospitallocation")
    public String hello() {
        return "hospitallocation";
    }
}
