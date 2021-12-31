package com.birdsgenesis.rest;

import com.birdsgenesis.dto.nft.Nft;
import com.birdsgenesis.exception.ProjectNotFoundException;
import com.birdsgenesis.service.AcrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class AcrController {

    @Autowired
    private List<AcrService> acrServices;

    @GetMapping("/acr")
    public @ResponseBody
    List<? extends Nft> list(@RequestParam @Valid @NotNull String project, @RequestParam(defaultValue = "") List<Integer> ids, @RequestParam(defaultValue = "999999") Integer limit) {
        return getAcrService(project).getNfts(ids, limit);
    }

    @GetMapping("/acr/projects")
    List<String> projects() {
        return acrServices.stream().map(AcrService::getProjectName).collect(Collectors.toList());
    }

    @GetMapping("/acr/attributes")
    public @ResponseBody
    Set<String> list(@RequestParam @Valid @NotNull String project, @RequestParam(defaultValue = "") String type) throws IllegalAccessException {
        return getAcrService(project).getList(type);
    }

    private AcrService getAcrService(String project) {
        Optional<AcrService> acrService = acrServices.stream().filter(service -> project.equals(service.getProjectName())).findAny();

        if (acrService.isPresent()) {
            return acrService.get();
        } else {
            throw new ProjectNotFoundException();
        }

    }
}
