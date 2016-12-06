package jbehave.scenariosteps;

import java.util.List;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.unitils.reflectionassert.ReflectionAssert;

import com.booking.structure.beans.HotelSearchCriteriaBean;
import com.booking.structure.beans.SearchResultItemBean;
import com.booking.util.JbehaveUtil;

import net.thucydides.core.annotations.Steps;
import serenity.steps.HotelSearchPageSteps;
import serenity.steps.SearchResultPageSteps;

public class BookingSearchScenario {

    @Steps
    private HotelSearchPageSteps hotelSearchPageSteps;

    @Steps
    SearchResultPageSteps searchResultPageSteps;

    @Given("user has opened 'Booking.com' site")
    public void userOpenedSite() {
        hotelSearchPageSteps.openPage();
    }

    @When("user performs hotels search for current month, using following criterias: $table")
    public void searchHotelsByCriterias(final ExamplesTable table) {
        final HotelSearchCriteriaBean hotelSearchCriteria = JbehaveUtil.populate(table, HotelSearchCriteriaBean.class).get(0);
        hotelSearchPageSteps.fillInTravelingInfo(hotelSearchCriteria);
    }

    @Then("user shuold see following found hotels: $table")
    public void isSearchHotelsLocatedInNy(final ExamplesTable examplesTable) {
        final List<SearchResultItemBean> searchResultItem = JbehaveUtil.populate(examplesTable, SearchResultItemBean.class);
        ReflectionAssert.assertReflectionEquals("There are incorrect List of Hotels displayed!!!",
                searchResultItem, searchResultPageSteps.getHotelsList());
    }
}
