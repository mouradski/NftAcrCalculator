# Getting Started

### Start API
```
mvn spring-boot:run
```

### Adding a new Nft project

Add a new class java/com/birdsgenesis/dto/nft/NewProject.java
```java
package com.birdsgenesis.dto.nft;

import com.birdsgenesis.dto.meta.Metadata;
import com.birdsgenesis.dto.meta.NftAttribute;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewProject extends Nft {

    // Project NFT must have an attribute like 'New Attribute Name' or 'newAttributeName' etc, remove space and use camel case
    private NftAttribute newAttributeName;

    private NftAttribute newAttributeName2;
    private NftAttribute newAttributeName3;
    
    //....... 
    
    public NewProject(Metadata metadata) throws IllegalAccessException {
        super(metadata);
    }
}
```

Add a service implementation class java/com/birdsgenesis/service/NewProjectAcrService.java
```java
package com.birdsgenesis.service;

import com.birdsgenesis.dto.nft.NewProject;
import com.birdsgenesis.utils.NftHelper;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class NewProjectAcrService extends AcrService<Bazooka> {

    @Override
    public String getProjectName() {
        return "NewProjectName";
    }

    @Override
    protected Class<NewProject> getNftType() {
        return NewProject.class;
    }

    @Override
    protected String getMetaUrl() {
        // You can find in the smart contract
        return "URL_TO_METADATA.json";
    }

    @Override
    protected Function<Object, Double> acrFunction() {
        return NftHelper.statisticalRarityScore();
    }
}
```

And that's all

### Api 
list available projects
```
http://localhost:8080/acr/projects
```

get Acr 
```
http://localhost:8080/acr?proejct=PROJECT_NAME
http://localhost:8080/acr?proejct=PROJECT_NAME&ids=1,2,3,4555
http://localhost:8080/acr?proejct=PROJECT_NAME&ids=1,2,3,4555&limit=1000
```

### Support 
XRP : rBP61sGR21Bxq9mxTyYeQoWKY3G8hHAt2u

SGB : 0x147094aFf1F4911555dd1c6881395e1786864048

Contact : https://twitter.com/mouradski

Website : https://birds-genesis.com