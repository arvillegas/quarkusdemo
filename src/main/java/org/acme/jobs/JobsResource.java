package org.acme.jobs;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.acme.employee.model.Employee;
import org.acme.employee.model.Jobs;

@Path("/job")
public class JobsResource
{
    @Inject
    JobsService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createJobsService(Jobs job) {
        service.createJob(job);
    }
}
