package task;

import com.idf.idftestproject.model.Cryptocurrency;
import com.idf.idftestproject.model.User;
import com.idf.idftestproject.service.CryptocurrencyService;
import com.idf.idftestproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
@Scope("prototype")
public class RetrieveCryptocurrencyPriceTask extends Thread {

    private static final int SURVEY_PERIOD = 2000;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private CryptocurrencyService cryptocurrencyService;

    @Override
    public void run() {
        try {
            while (true) {
                final List<Cryptocurrency> cryptocurrencies = cryptocurrencyService.retrieveCryptocurrencyPrice();
                final List<User> users = userService.findAll();
                if (!cryptocurrencies.isEmpty() && !users.isEmpty()) {
                    final Map<User, BigDecimal> usersWithChangePrices = cryptocurrencyService.calculateChangingCryptocurrency(cryptocurrencies, users);
                    for (Map.Entry<User, BigDecimal> entry : usersWithChangePrices.entrySet()) {
                        logger.warn("Changes! " + entry.getKey().getName() + " " + entry.getKey().getSymbol() + " " +
                                entry.getValue().toString());
                    }
                }
                Thread.sleep(SURVEY_PERIOD);
            }
        } catch (InterruptedException e) {
            logger.warn("Interrupted thread-demon RetrieveCryptocurrencyPriceTask", e);
            e.printStackTrace();
        }
    }
}
