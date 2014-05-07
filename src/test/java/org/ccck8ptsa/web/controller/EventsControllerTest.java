package org.ccck8ptsa.web.controller;

import org.ccck8ptsa.persistence.entity.Event;
import org.ccck8ptsa.service.api.EventService;
import org.ccck8ptsa.web.forms.EventForm;
import org.ccck8ptsa.web.forms.NewsEventForm;
import org.ccck8ptsa.web.forms.SponsorForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by &quot;The Collective&quot; on 5/6/14.
 */
@RunWith(PowerMockRunner.class)
public class EventsControllerTest {

    public static final String TEST_ID = "TEST ID";
    public static final int NUMBER_UPDATED_SPONSORS = 2;
    public static final int INDEX_OF_NEWSEVENT = 0;
    @InjectMocks
    private EventsController eventsController = new EventsController();

    @Mock
    private HttpServletRequest mockServletRequest;

    @Mock
    private EventService mockEventService;

    @Mock
    private EventForm mockEventForm;

    @Mock
    private List<NewsEventForm> mockNewsEventForms;

    @Mock
    private Timestamp mockTimestamp;

    @Mock
    private List<SponsorForm> mockSponsorForms;

    @Mock
    private SponsorForm mockSponsorForm;

    @Mock
    private Iterator<SponsorForm> mockSponsorIterator;

    @Mock
    private NewsEventForm mockNewsEventForm;


    @Test
    public void whenCreatingEventWithoutNews_expectEventCreated() throws Exception{
        // Assemble
        when(mockEventForm.getId()).thenReturn(TEST_ID);
        when(mockEventForm.getNewsEventForms()).thenReturn(null);

        // Act
        Event testEvent = eventsController.createEvent(mockEventForm);

        // Assert
        assertEventValid(testEvent);
    }

    @Test
    public void whenCreatingEventWithNews_expectEventCreated() throws Exception{
        assembleNewsEvent();

        // Act
        Event testEvent = eventsController.createEvent(mockEventForm);

        // Assert
        assertValidNewsEvent(testEvent);
    }

    private void assertValidNewsEvent(Event testEvent) {
        assertEventValid(testEvent);
        assertNotNull("News event is null",testEvent.getNewsEvents().get(0));
    }


    @Test
    public void whenCreatingEventWithNewsAndSponsor_expectEventCreated(){
        // Assemble
        assembleEventWithNewsEventAndSponsor();

        // Act
        Event testEvent = eventsController.createEvent(mockEventForm);

        // Assert
        assertValidNewsEventAndSponsor(testEvent);
    }

    private void assertValidNewsEventAndSponsor(Event testEvent) {
        assertValidNewsEvent(testEvent);
        assertTrue("Sponsor not populated",!CollectionUtils.isEmpty(testEvent.getSponsors()));
    }


    @Test
    public void whenAddingSponsorToEvent_expectMultipleSponsors(){
        // Assemble
        assembleEventWithNewsEventAndSponsor();
        List<SponsorForm> sponsorForms = new ArrayList<SponsorForm>();
        sponsorForms.add(mockSponsorForm);
        sponsorForms.add(mockSponsorForm);
        when(mockEventForm.getSponsorForms()).thenReturn(sponsorForms);


        // Act
        Event updatedEvent = eventsController.updateEvent(mockEventForm);

        // Assert
        assertValidNewsEventAndSponsor(updatedEvent);
        assertEquals("Wrong number of sponsors", NUMBER_UPDATED_SPONSORS,updatedEvent.getSponsors().size());
    }

    //==== Privates

    private void assertEventValid(Event testEvent) {
        assertNotNull("Test event null",testEvent);
        assertNotNull("EventId is null",testEvent.getId());
    }

    private void assembleNewsEvent() {
        when(mockEventForm.getNewsEventForms()).thenReturn(mockNewsEventForms);
        when(mockEventForm.getId()).thenReturn(TEST_ID);
        when(mockNewsEventForms.get(INDEX_OF_NEWSEVENT)).thenReturn(mockNewsEventForm);
        when(mockNewsEventForms.get(INDEX_OF_NEWSEVENT).getCreateDate()).thenReturn(mockTimestamp);
        when(mockNewsEventForms.get(INDEX_OF_NEWSEVENT).getModifyDate()).thenReturn(mockTimestamp);
        List<NewsEventForm> newsEventForms = new ArrayList<NewsEventForm>();
        when(mockEventForm.getNewsEventForms()).thenReturn(newsEventForms);
        newsEventForms.add(mockNewsEventForm);
    }

    private void assembleEventWithNewsEventAndSponsor() {
        assembleNewsEvent();
        when(mockEventForm.getSponsorForms()).thenReturn(mockSponsorForms);
        List<SponsorForm> sponsorForms = new ArrayList<SponsorForm>();
        sponsorForms.add(mockSponsorForm);
        when(mockEventForm.getSponsorForms()).thenReturn(sponsorForms);
    }
}
