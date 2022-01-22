package com.birdsgenesis.service;

import com.birdsgenesis.dto.nft.Nft;
import com.birdsgenesis.exception.ProjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AcrService {

    private List<AbstractAcrService> acrServices;

    public AcrService(@Autowired List<AbstractAcrService> acrServices) {
        this.acrServices = acrServices;
    }

    public <T extends Nft> List<T> getNfts(String project, List<Integer> ids, Integer limit) {
        return getAcrService(project).getNfts(ids, limit);
    }

    public Set<String> listProjects() {
        return acrServices.stream().map(AbstractAcrService::getProjectName).collect(Collectors.toSet());
    }

    public Set<String> listAttributeValues(String project, String attributeName) throws IllegalAccessException {
        return getAcrService(project).getAttributeValues(attributeName);
    }

    private AbstractAcrService getAcrService(String project) {
        Optional<AbstractAcrService> acrService = acrServices.stream().filter(service -> project.equals(service.getProjectName())).findAny();

        if (acrService.isPresent()) {
            return acrService.get();
        } else {
            throw new ProjectNotFoundException();
        }
    }

}
