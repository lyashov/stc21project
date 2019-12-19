package ru.innopolis.stc21.med.controller;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.stc21.med.model.ClinicEntity;
import ru.innopolis.stc21.med.model.MedicalHistoryEntity;
import ru.innopolis.stc21.med.service.ClinicService;
import ru.innopolis.stc21.med.service.GeoIP;
import ru.innopolis.stc21.med.service.GeoIPService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class GeoIPController {

    ClinicService clinicService;

    @Autowired
    public GeoIPController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @RequestMapping(value = "/geoip", method = {RequestMethod.GET,RequestMethod.POST})
    public String geoIpLocation(
            HttpServletRequest httpServletRequest
    ) throws Exception {
        //String ipAddress = httpServletRequest.getRemoteAddr();
        String ipAddress = "188.130.155.153";

        System.out.println(ipAddress);

        GeoIPService locationService
                = new GeoIPService();
        GeoIP userGeoPosition = locationService.getLocation(ipAddress);
        return "redirect:/hospitallocation";
    }

    @GetMapping("/hospitallocation")
    public String hello(Model model) {

        List<ClinicEntity> clinicEntities = clinicService.getAllClinics();
        List<ClinicEntity> sortedClinicEntities = clinicsSort(clinicEntities, userGeoPosition);
        model.addAttribute("clinicEntities", sortedClinicEntities);
        return "hospitallocation";
    }

    private List<ClinicEntity> clinicsSort (List<ClinicEntity> list, GeoIP geo){
        Map<Double, ClinicEntity> map = new TreeMap<>();
        List<ClinicEntity> result = new ArrayList<>();

        double geoX = Double.parseDouble(geo.getLatitude());
        double geoY = Double.parseDouble(geo.getLongitude());

        for (ClinicEntity clinic : list){
            double clinicX = Double.parseDouble(clinic.getCoord_x());
            double clinicY = Double.parseDouble(clinic.getCoord_y());

            double distance = Math.sqrt(Math.pow(clinicX - geoX,2) + Math.pow(clinicY - geoY, 2));
            map.put(distance, clinic);
        }

        for(Map.Entry<Double, ClinicEntity> entry : map.entrySet()){
            result.add(entry.getValue());
        }
        return result;
    }


}
