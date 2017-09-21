package hello.storage;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import com.assignment.exception.FileStorageNotFoundException;
import com.assignment.service.FileStorageService;
import com.assignment.service.FileStorageServiceImpl;

public class FileStorageServiceTests {

	private FileStorageService service;

	@Before
	public void init() {
		service = new FileStorageServiceImpl();
		service.createRootDir();
		service.deleteAllFiles();
	}

	@Test(expected = FileStorageNotFoundException.class)
	public void testGetFileForNonExistent() {
		service.getFile("test.json");
	}

	@Test
	public void saveAndLoad() {
		service.saveFile(
				new MockMultipartFile("test", "test.json", MediaType.TEXT_PLAIN_VALUE, "Hello World".getBytes()));
		assertThat(service.getFile("test.json")).exists();
	}

	@Test
	public void getAllFiles() {
		service.saveFile(
				new MockMultipartFile("test", "test.json", MediaType.TEXT_PLAIN_VALUE, "Hello World".getBytes()));
		assertThat(service.getAllFiles().size()).isEqualTo(1);
		service.deleteAllFiles();
		assertThat(service.getAllFiles().size()).isEqualTo(0);
	}
	
	@Test
	public void getDeleteAllFiles() {
		assertThat(service.getAllFiles().size()).isEqualTo(0);
		service.saveFile(
				new MockMultipartFile("test", "test.json", MediaType.TEXT_PLAIN_VALUE, "Hello World".getBytes()));
		service.deleteAllFiles();
		assertThat(service.getAllFiles().size()).isEqualTo(0);
	}
}