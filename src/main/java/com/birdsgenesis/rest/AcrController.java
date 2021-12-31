package com.birdsgenesis.rest;

import com.birdsgenesis.dto.nft.Nft;
import com.birdsgenesis.service.AcrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@RestController
public class AcrController {

    private AcrService acrService;

    public AcrController(@Autowired AcrService acrService) {
        this.acrService = acrService;
    }

    @GetMapping("/acr")
    public @ResponseBody
    List<? extends Nft> getNfts(@RequestParam @Valid @NotNull String project, @RequestParam(defaultValue = "") List<Integer> ids, @RequestParam(defaultValue = "999999") Integer limit) {
        return acrService.getNfts(project, ids, limit);
    }

    @GetMapping("/acr/projects")
    Set<String> listProjects() {
        return acrService.listProjects();
    }

    @GetMapping("/acr/attributes")
    public @ResponseBody
    Set<String> listAttributes(@RequestParam @Valid @NotNull String project, @RequestParam(defaultValue = "") String attribute) throws IllegalAccessException {
        return acrService.listAttributeValues(project, attribute);
    }
}
