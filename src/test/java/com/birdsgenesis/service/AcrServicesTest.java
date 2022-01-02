package com.birdsgenesis.service;

import com.birdsgenesis.dto.nft.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
public class AcrServicesTest {

    @Autowired
    private AcrService acrService;

    @Test
    public void testListProjects() {
        Set<String> projects = acrService.listProjects();
        Assertions.assertEquals(5, projects.size());
        Assertions.assertEquals(Arrays.asList("BazookaChicks", "BirdsGenesis", "PrettyPandas", "SongbirdPunks", "CosmicBombers").stream().collect(Collectors.toSet()), projects);
    }

    @Test
    public void testGetNfts() {
        List<Bazooka> bazookaChicks = acrService.getNfts("BazookaChicks", new ArrayList<>(), 90000);
        Assertions.assertEquals(10000, bazookaChicks.size());
        Assertions.assertEquals(1, bazookaChicks.get(0).getAcr());
        Assertions.assertEquals(1485, bazookaChicks.stream().filter(nft -> nft.getId().equals(5000)).findFirst().get().getAcr());
        Assertions.assertEquals(10000, bazookaChicks.get(9999).getAcr());

        List<Bird> birdsGenesis = acrService.getNfts("BirdsGenesis", new ArrayList<>(), 90000);
        Assertions.assertEquals(9999, birdsGenesis.size());
        Assertions.assertEquals(1, birdsGenesis.get(0).getAcr());
        Assertions.assertEquals(2989, birdsGenesis.stream().filter(nft -> nft.getId().equals(5000)).findFirst().get().getAcr());
        Assertions.assertEquals(9999, birdsGenesis.get(9998).getAcr());

        List<Panda> prettyPandas = acrService.getNfts("PrettyPandas", new ArrayList<>(), 90000);
        Assertions.assertEquals(10000, prettyPandas.size());
        Assertions.assertEquals(1, prettyPandas.get(0).getAcr());
        Assertions.assertEquals(2111, prettyPandas.stream().filter(nft -> nft.getId().equals(5000)).findFirst().get().getAcr());
        Assertions.assertEquals(10000, prettyPandas.get(9999).getAcr());

        List<Punk> songbirdPunks = acrService.getNfts("SongbirdPunks", new ArrayList<>(), 90000);
        Assertions.assertEquals(20000, songbirdPunks.size());
        Assertions.assertEquals(1, songbirdPunks.get(0).getAcr());
        Assertions.assertEquals(1762, songbirdPunks.stream().filter(nft -> nft.getId().equals(5000)).findFirst().get().getAcr());
        Assertions.assertEquals(20000, songbirdPunks.get(19999).getAcr());

        List<Bomber> bombers = acrService.getNfts("CosmicBombers", new ArrayList<>(), 90000);
        Assertions.assertEquals(1000, bombers.size());
        Assertions.assertEquals(1, bombers.get(0).getAcr());
        Assertions.assertEquals(1000, bombers.get(999).getAcr());
    }
}
