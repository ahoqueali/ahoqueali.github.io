package org.ahoque.resources;

import io.dropwizard.hibernate.UnitOfWork;
import org.ahoque.api.AccountDto;
import org.ahoque.api.TransactionDto;
import org.ahoque.core.AccountService;
import org.ahoque.core.TransactionService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    private final AccountService accountService;
    private final TransactionService transactionService;

    public AccountResource(final AccountService accountService,
                           final TransactionService transactionService) {

        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAccount(@NotNull @Valid AccountDto accountDto) throws IllegalArgumentException {

        AccountDto dto = accountService.create(accountDto);
        URI locationUri =  UriBuilder.fromResource(AccountResource.class).path("{accountNumber}").build(dto.getAccountNumber());
        return Response
                .created(locationUri)
                .entity(dto)
                .build();
    }

    @GET
    @Path("/{accountNumber}")
    @UnitOfWork
    public Response getAccount(@PathParam("accountNumber") String number) throws IllegalArgumentException {

        AccountDto dto = accountService.findByAccountNumber(number);
        return Response
                .status(200)
                .entity(dto)
                .build();
    }

    @POST
    @Path("/{accountNumber}/transactions")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveTransaction(@PathParam("accountNumber") String accountNumber,
                                   @NotNull @Valid TransactionDto transactionDto) throws IllegalArgumentException {

        TransactionDto savedTransactionDto = transactionService.save(accountNumber, transactionDto);
        URI locationUri =  UriBuilder
                .fromResource(AccountResource.class)
                .path("{accountNumber}/transactions/{id}")
                .build(accountNumber, savedTransactionDto.getId());

        return Response
                .created(locationUri)
                .entity(savedTransactionDto)
                .build();
    }

    @GET
    @Path("/{accountNumber}/transactions")
    @UnitOfWork
    public Response getAccountTransactions(@PathParam("accountNumber") String accountNumber){

        return Response
                .status(200)
                .entity(transactionService.getAllTransactions(accountNumber))
                .build();
    }

    @GET
    @Path("/{accountNumber}/balance")
    @UnitOfWork
    public Response getAccountBalance(@PathParam("accountNumber") String accountNumber){

        return Response
                .status(200)
                .entity(accountService.getBalance(accountNumber))
                .build();
    }
}
