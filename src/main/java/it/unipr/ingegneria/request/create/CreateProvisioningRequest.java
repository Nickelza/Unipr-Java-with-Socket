package it.unipr.ingegneria.request.create;

import it.unipr.ingegneria.request.ModelRequest;
import it.unipr.ingegneria.util.ModelRequestType;

public class CreateProvisioningRequest extends ModelRequest<CreateProvisioningRequest> {
    private String wineName;
    private Integer wineQuantity;

    @Override
    public CreateProvisioningRequest asType(ModelRequestType type) {
        this.type = type.toString();
        return this;
    }

    public String getWineName() {
        return wineName;
    }

    public CreateProvisioningRequest setWineName(String wineName) {
        this.wineName = wineName;
        return this;
    }

    public Integer getWineQuantity() {
        return wineQuantity;
    }

    public CreateProvisioningRequest setWineQuantity(Integer wineQuantity) {
        this.wineQuantity = wineQuantity;
        return this;
    }
}
