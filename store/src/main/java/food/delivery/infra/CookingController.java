package food.delivery.infra;

import food.delivery.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping(value="/cookings")
@Transactional
public class CookingController {

    @Autowired
    CookingRepository cookingRepository;

    @RequestMapping(
        value = "cookings/{id}/accept",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Cooking accept(
        @PathVariable(value = "id") Long id,
        @RequestBody AcceptCommand acceptCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /cooking/accept  called #####");
        Optional<Cooking> optionalCooking = cookingRepository.findById(id);

        optionalCooking.orElseThrow(() -> new Exception("No Entity Found"));
        Cooking cooking = optionalCooking.get();
        cooking.accept(acceptCommand);

        cookingRepository.save(cooking);
        return cooking;
    }

    @RequestMapping(
        value = "cookings/{id}/startcooking",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Cooking startCooking(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /cooking/startCooking  called #####");
        Optional<Cooking> optionalCooking = cookingRepository.findById(id);

        optionalCooking.orElseThrow(() -> new Exception("No Entity Found"));
        Cooking cooking = optionalCooking.get();
        cooking.startCooking();

        cookingRepository.save(cooking);
        return cooking;
    }

    @RequestMapping(
        value = "cookings/{id}/finishcooking",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Cooking finishCooking(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /cooking/finishCooking  called #####");
        Optional<Cooking> optionalCooking = cookingRepository.findById(id);

        optionalCooking.orElseThrow(() -> new Exception("No Entity Found"));
        Cooking cooking = optionalCooking.get();
        cooking.finishCooking();

        cookingRepository.save(cooking);
        return cooking;
    }
}
