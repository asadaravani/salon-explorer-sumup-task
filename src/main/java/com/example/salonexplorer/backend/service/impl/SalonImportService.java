package com.example.salonexplorer.backend.service.impl;

import com.example.salonexplorer.backend.client.GooglePlacesClient;
import com.example.salonexplorer.backend.client.dto.GooglePlaceDto;
import com.example.salonexplorer.backend.client.dto.GooglePlacesResponseDto;
import com.example.salonexplorer.backend.entity.Salon;
import com.example.salonexplorer.backend.mapper.GooglePlaceDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SalonImportService {
    GooglePlacesClient client;
    GooglePlaceDtoMapper mapper;

    public List<Salon> importAll(){
        List<GooglePlaceDto> list = searchWithQueries();
        List<GooglePlaceDto> deduplicatedList = deduplicate(list);
        return mapper.toEntities(deduplicatedList);
    }
    private List<GooglePlaceDto> deduplicate(List<GooglePlaceDto> list){
        Map<String, GooglePlaceDto> map = new HashMap<>();
        for(GooglePlaceDto dto : list){
            if (dto == null){
                continue;
            }
            map.put(dto.id(), dto);
        }
        return new ArrayList<>(map.values());
    }
    private List<GooglePlaceDto> searchWithQueries() {
        List<GooglePlaceDto> results = new ArrayList<>();
        List<String> queries = getQueries();
        for (String query : queries) {
            GooglePlacesResponseDto responseDto = client.searchForSalons(query);
            if (responseDto.places() == null || responseDto.places().isEmpty()) {
                continue;
            }
            results.addAll(responseDto.places());
        }
        return results;
    }

    private List<String> getQueries() {
        return List.of(
                "salon fryzjerski Warszawa",
                "salony fryzjerskie Warszawa",
                "hair salon Warsaw",
                "beauty salon Warsaw",
                "salon urody Warszawa",

                "fryzjer Warszawa Mokotów",
                "fryzjer Warszawa Śródmieście",
                "fryzjer Warszawa Wola",
                "fryzjer Warszawa Praga Południe",
                "fryzjer Warszawa Praga Północ",
                "fryzjer Warszawa Ursynów",
                "fryzjer Warszawa Wilanów",
                "fryzjer Warszawa Ochota",
                "fryzjer Warszawa Żoliborz",
                "fryzjer Warszawa Bemowo",
                "fryzjer Warszawa Bielany",
                "fryzjer Warszawa Targówek",
                "fryzjer Warszawa Białołęka",
                "fryzjer Warszawa Ursus",
                "fryzjer Warszawa Włochy",
                "fryzjer Warszawa Rembertów",
                "fryzjer Warszawa Wawer",
                "fryzjer Warszawa Wesoła",

                "hairdresser Warsaw Mokotow salons",
                "hairdresser Warsaw Srodmiescie salons",
                "hairdresser Warsaw Wola salons",
                "barber Warsaw Mokotów",
                "barber Warsaw Śródmieście",
                "barber Warsaw Praga",

                "beauty salon Mokotow Warsaw",
                "beauty salon Srodmiescie Warsaw",
                "beauty salon Wola Warsaw",

                "salon kosmetyczny Warszawa Mokotów",
                "salon kosmetyczny Warszawa Śródmieście",
                "salon kosmetyczny Warszawa Wola",

                "hair studio Warsaw Mokotow",
                "hair studio Warsaw Srodmiescie",

                "fryzjer damski Warszawa",
                "fryzjer męski Warszawa",
                "salon fryzjerski damski Warszawa",
                "salon fryzjerski męski Warszawa"
        );
    }
}
