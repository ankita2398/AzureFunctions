package aic.az.function.kcamera;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with Azure Blob trigger.
 */
public class BlobTriggerFunction {
    /**
     * This function will be invoked when a new or updated blob is detected at the specified path. The blob contents are provided as input to this function.
     */
    @FunctionName("BlobTrigger-Java")
    @StorageAccount("MyStorageConnectionAppSetting")
    public void run(
        @BlobTrigger(name = "content", path = "Containers/images", dataType = "binary") byte[] content,
        @BindingName("name") String name,
        @BlobInput(name = "inputBlob", path = "Containers/images", dataType = "binary") byte[] inputBlob,
        @BlobOutput(name = "outputBlob", path = "Containers/thumbnails", dataType = "binary") OutputBinding<byte[]> outputBlob,
        final ExecutionContext context
    ) {
        context.getLogger().info("Java Blob trigger function processed a blob. Name: " + name + "\n  Size: " + content.length + " Bytes");
        outputBlob.setValue(inputBlob);
    }
}
