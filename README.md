# RectangleGridLayout
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.channguyen/rl/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.channguyen/rl)

RectangleGridLayout is a container that arranges views into a gird of rectangles of the same sized. Note, this is NOT a meant to be replaced 
GridView or TableLayout/GridLayout, rather it's a much less sophisticated container to hold views of the same size. The goal is 
to provide a grid-like container with better performance than says TableLayout.     

# Screenshots
![Main screen](/screenshots/sc0.png)

# Features
- Support dynamic sizing with margins

# Usage
Add a dependency to your `build.gradle`:
```
dependencies {
    compile 'com.github.channguyen:rl:1.0.0'
}
```
Add the `com.github.channguyen.rl.RectangleGridLayout` to your layout XML file.
```XML
<com.github.channguyen.rl.RectangleGridLayout
    android:layout_width="200dp"
    android:layout_height="300dp"
    app:rgl___column="4"
    >
    <Button
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:text="a"
      />
    <Button
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:text="b"
      />
    <Button
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:text="c"
      />
    <Button
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:text="d"
      />
    <Button
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:text="e"
      />
    <Button
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:text="f"
      />
</com.github.channguyen.rl.RectangleLayout>
```

For more usage examples check the **sample** project.

# License
```
Copyright 2015 Chan Nguyen

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
