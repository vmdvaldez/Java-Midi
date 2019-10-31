# Java-Midi

### Install OpenJDK-8

`sudo apt-get update`
`sudo apt install openjdk-8-jdk`

### Enable JavaFX libraries

```
sudo apt purge openjfx
sudo apt install openjfx=8u161-b12-1ubuntu2 libopenjfx-jni=8u161-b12-1ubuntu2 libopenjfx-java=8u161-b12-1ubuntu2
sudo apt-mark hold openjfx libopenjfx-jni libopenjfx-java
```

### Compiling as JAR file

`jar cvfe ${NAME}.jar audio.Gui audio/*.class`

to run:

`java -jar ${NAME}.jar`


### Enabling CUDA

# Add NVIDIA package repositories
wget https://developer.download.nvidia.com/compute/cuda/repos/ubuntu1804/x86_64/cuda-repo-ubuntu1804_10.0.130-1_amd64.deb
sudo dpkg -i cuda-repo-ubuntu1804_10.0.130-1_amd64.deb
sudo apt-key adv --fetch-keys https://developer.download.nvidia.com/compute/cuda/repos/ubuntu1804/x86_64/7fa2af80.pub
sudo apt-get update
wget http://developer.download.nvidia.com/compute/machine-learning/repos/ubuntu1804/x86_64/nvidia-machine-learning-repo-ubuntu1804_1.0.0-1_amd64.deb
sudo apt install ./nvidia-machine-learning-repo-ubuntu1804_1.0.0-1_amd64.deb
sudo apt-get update

# Install NVIDIA driver
sudo apt-get install --no-install-recommends nvidia-driver-418
# Reboot. Check that GPUs are visible using the command: nvidia-smi


# Install development and runtime libraries (~4GB)
sudo apt-get install --no-install-recommends \
    cuda-10-0 \
    libcudnn7=7.6.2.24-1+cuda10.0  \
    libcudnn7-dev=7.6.2.24-1+cuda10.0


# Install TensorRT. Requires that libcudnn7 is installed above.
sudo apt-get install -y --no-install-recommends libnvinfer5=5.1.5-1+cuda10.0 \
    libnvinfer-dev=5.1.5-1+cuda10.0


### Installing tensorflow (1.15.*) & dependencies

pip install tensorflow==1.15.*      # CPU
pip install tensorflow-gpu==1.15.*  # GPU

pip install pillow Cython lxml jupyter matplotlib


### Object detection model (Note always need to run these commands to be able to avoid module errors)

git clone https://github.com/tensorflow/models.git

cd <tf-github-path>/models/research/
protoc object_detection/protos/*.proto --python_out=.
export PYTHONPATH=$PYTHONPATH:`pwd`:`pwd`/slim

# to test
python object_detection/builders/model_builder_test.py




### Installing LabelIMG

sudo apt-get install pyqt4-dev-tools
sudo pip install lxml
make qt4py2
sudo python labelImg.py

### Using Pre-trained model (customizing to allow symbol detection?)

Find config file under tensor flow github page for different models (<tensor-flow>object_detection/samples/configs/*.config) the choice varies depending on the wanted model

Download the model file assocatied with config file you've chosen. (it's a tar file must untar i.e. tar -xvf <file-name>)
	- this can be found under g3doc folder markdown file.

you must change the .record file for personal/custom use of the model chosen.

# Workaround when training, it may use all your GPU's memory that may lead to ERRORS

add after session_config = tf.ConfigProto(...) in training.py
`session_config.gpu_options.per_process_gpu_memory_fraction = 0.8`



## References
-	https://www.tutorialspoint.com/javafx/javafx_application.htm
-	https://skeoop.github.io/javafx/Event-Handling.pdf
-	https://www.tensorflow.org/install/gpu		(Installing CUDA)
-	https://github.com/tensorflow/tensorflow	(Installin Tensor-flow)	
- 	https://github.com/tzutalin/labelImg		(Installing LabelIMG)
-	https://medium.com/coinmonks/tensorflow-object-detection-with-custom-objects-34a2710c6de5
-	https://pythonprogramming.net/training-custom-objects-tensorflow-object-detection-api-tutorial/?completed=/creating-tfrecord-files-tensorflow-object-detection-api-tutorial/
