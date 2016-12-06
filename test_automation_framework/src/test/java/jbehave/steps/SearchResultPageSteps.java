package jbehave.steps;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.booking.structure.beans.SearchResultItemBean;
import com.booking.structure.core.pages.SearchResultPage;
import com.booking.util.BeanUtil;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;

public class SearchResultPageSteps extends ScenarioSteps {

    private final SearchResultPage searchResultPage;

    public SearchResultPageSteps(final Pages pages) {
        super(pages);
        searchResultPage = getPages().getPage(SearchResultPage.class);
    }

    @Step
    public boolean isAllHotelsLocatedIn(final String location) {
        return getHotelsList().stream()
                .allMatch(item -> item.getHotelLocation().contains(location));
    }

    private List<SearchResultItemBean> getHotelsList() {
        return searchResultPage.getResultsPanel().getResultItems().map(panel -> {
            final SearchResultItemBean hotel = new SearchResultItemBean();
            BeanUtil.copyProperties(hotel, panel);
            return hotel;
        }).collect(toList());
    }
}
