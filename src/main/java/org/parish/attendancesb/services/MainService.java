package org.parish.attendancesb.services;

import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.access.User;
import org.parish.attendancesb.services.interfaces.CatequesisSearchService;
import org.parish.attendancesb.services.interfaces.CatequesisService;
import org.parish.attendancesb.services.interfaces.Service;
import org.parish.attendancesb.services.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MainService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Catequesis catequesis;

    private boolean isAutorizeAllCatequesis;

    private SessionService sessionService;

    private UserService userService;

    private CatequesisService catequesisService;

    private CatequesisSearchService catequesisSearchService;

    public MainService(
            SessionService sessionService,
            UserService userService,
            CatequesisService catequesisService,
            CatequesisSearchService catequesisSearchService
    ) {
        this.userService = userService;
        this.catequesisService = catequesisService;
        this.sessionService = sessionService;
        this.catequesisSearchService = catequesisSearchService;
        this.sessionService.setCatequesis(
                this.catequesisService.findByUser(sessionService.getUser()).stream().findFirst().get()
        );
        catequesis = this.sessionService.getCatequesis();
    }

    public Catequesis getCatequesis() {
        if (hasOne())
            return getCatequesisList().stream().findFirst().get();

        return catequesis;
    }

    public void setCatequesis(Catequesis catequesis) {
        this.catequesis = catequesis;
    }

    public User getUser() {
        return sessionService.getUser();
    }

    public void setAutorizeAllCatequesis(boolean autorizeAllCatequesis) {
        isAutorizeAllCatequesis = autorizeAllCatequesis;
    }

    public Service getCatequesisSearchService() {
        catequesisSearchService.setCatequesisList(getCatequesisList());
        return catequesisSearchService;
    }

    public String getReceiverPersonTypePlural() {
        sessionService.setCatequesis(catequesis);
        return sessionService.getReceiverPersonTypePlural();
    }

    public boolean authorize(String role) {
        return userService.authorize(role);
    }

    public boolean authenticate(String username, String password) {
        return userService.authenticate(username, password);
    }

    public boolean hasOne() {
        return this.count() == 1;
    }

    public boolean hasMany() {
        return this.count() > 1;
    }

    public List<Catequesis> getCatequesisList() {
//        List<Catequesis> catequesisList = new ArrayList<>();
//        catequesisService.findAll().forEach(catequesis1 -> {
//            catequesis1.getCatequistas()
//                    .forEach(catequista -> {
//                        if (catequista.getUser() != null && catequista.getUser().equals(sessionService.getUser())) {
//                            catequesisList.add(catequesis1);
//                        }
//                    });
//        });
//        return catequesisList;
//        catequesisService.findByUser(sessionService.getUser()).forEach(catequesis1 -> logger.info("-->{}",catequesis1.getCatequistas()));

        if (isAutorizeAllCatequesis) {
            return catequesisService.findAll();
        }

        return catequesisService.findByUser(sessionService.getUser());
    }

    private long count() {
        return catequesisService.countByUsers(sessionService.getUser());
    }

}
