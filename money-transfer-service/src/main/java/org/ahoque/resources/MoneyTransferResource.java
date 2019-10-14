package org.ahoque.resources;

import io.dropwizard.hibernate.UnitOfWork;
import org.ahoque.api.MoneyTransferDto;
import org.ahoque.core.MoneyTransferService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/transfer-money")
public class MoneyTransferResource {

    private final MoneyTransferService moneyTransferService;

    public MoneyTransferResource(final MoneyTransferService moneyTransferService){
        this.moneyTransferService = moneyTransferService;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response transferMoney(@NotNull @Valid MoneyTransferDto moneyTransferDto){

       return Response.ok(moneyTransferService.moveMoney(moneyTransferDto))
               .build();

    }
}
