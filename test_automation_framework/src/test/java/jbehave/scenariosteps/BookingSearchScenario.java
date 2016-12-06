package jbehave.scenariosteps;

import java.util.List;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.junit.Assert;
import org.unitils.reflectionassert.ReflectionAssert;

import com.booking.structure.beans.HotelSearchCriteriaBean;
import com.booking.structure.beans.SearchResultItemBean;
import com.booking.util.JbehaveUtil;

import net.thucydides.core.annotations.Steps;
import jbehave.steps.HotelSearchPageSteps;
import jbehave.steps.SearchResultPageSteps;

public class BookingSearchScenario {

    @Steps
    private HotelSearchPageSteps hotelSearchPageSteps;

    @Steps
    private SearchResultPageSteps searchResultPageSteps;

    @Given("user has opened 'Booking.com' site")
    public void userOpenedSite() {
        hotelSearchPageSteps.openPage();
    }

    @When("user performs hotels search for current month, using following criterias: $table")
    public void searchHotelsByCriterias(final ExamplesTable table) {
        final HotelSearchCriteriaBean hotelSearchCriteria = JbehaveUtil.populate(table, HotelSearchCriteriaBean.class).get(0);
        hotelSearchPageSteps.fillInTravelingInfo(hotelSearchCriteria);
    }

    @Then("user should see found hotels, located in '$location'")
    public void isSearchHotelsLocatedIn(final String location) {
        Assert.assertTrue("There are incorrect List of Hotels displayed!!!",
                searchResultPageSteps.isAllHotelsLocatedIn(location));
    }
}
