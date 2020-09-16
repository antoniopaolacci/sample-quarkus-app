package it.sample.services.employee.storage;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

public class StorageServiceProducer {

	private static final String INMEM = "inmem";
	private static final String PERSISTENT = "persistent";

	private final String storageType;

	@Inject
	public StorageServiceProducer(@ConfigProperty(name = "sample.storage.type") String storageType) {
		this.storageType = storageType;
	}

	@Produces
	@ConfiguredStorage
	public StorageService createStorage(@Any Instance<StorageService> instance) throws Exception {
		
		if (StorageServiceProducer.PERSISTENT.equals(storageType)) {
			return instance.select(PersistentStorageService.class).get();
		} else if (StorageServiceProducer.INMEM.equals(storageType)) {
			return instance.select(InMemoryStorageService.class).get();
		}
		
		throw new Exception("Verify your storage configuration [sample.storage.type] variable.");
		
	}

}