package it.sample.services.employee.health;
import it.sample.services.employee.storage.ConfiguredStorage;
import it.sample.services.employee.storage.StorageService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;


@Readiness
@ApplicationScoped
public class ReadinessCheck implements HealthCheck {

	private final StorageService storageService;

	@Inject
	public ReadinessCheck(@ConfiguredStorage StorageService storageService) {
		this.storageService = storageService;
	}

	@Override
	public HealthCheckResponse call() {
		
		final HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Storage Service");
		
		if (storageService != null && storageService.isReady()) {
			responseBuilder.up();
		} else {
			responseBuilder.down();
		}
		
		return responseBuilder.build();
	}

}