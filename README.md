<h1>Clothing store management application</h1>
<p>
  Author: Daniel Pires<br>
  Description: clothing store application with CRUD operations. 
</p>

<h3>&#128187; Technologies used:</h3>
<ul>
  <li>Java</li>
  <li>Spring Boot</li>
  <li>Maven</li>
  <li>PostgreSQL</li>
  <li>JUnit</li>
  <li>Docker</li>
</ul>

<h3>&#128203; Pendent tasks:</h3>
<ul>
  <li>Add front-end pages using React</li>
  <li>Add Swagger page with endpoints</li>
</ul>

<h3>&#128191; How to install:</h3>
<h4>Requirements:</h4>
<ul>
  <li>OpenJDK 17 or superior</li>
  <li>Apache Maven</li><br>
  <p>Ensure that you have these two programs installed in your PC and that their variables are defined properly.</p>
  <p>
    1. With your terminal open inside the project folder, execute:<br>
    <code>mvn clean install</code><br>
    2. To run the generated .jar file, execute:<br>
    <code>java -jar ./target/clothing-store.0.0.1.SNAPSHOT.jar</code><br>
  </p>
</ul>

<hr>

<h3>&#128270; Endpoints:</h3>
<h3><code>GET:</code></h3>
<h4>clothings:</h4>
<ul>
  <li><code>/clothings</code>: returns all clothings.</li>
  <li><code>/clothings?name=ANY_NAME</code>: returns all clothings matching the given "name" URL parameter.</li>
  <li><code>/clothings?name=ANY_NAME&categories=ANY_CATEGORY</code>: returns all clothings matching the given "name" and "categories" URL parameters.<br>
    <strong>Note:</strong> you can add more than one category on the query. For example: <br>
    <code>/clothings?name=ANY_NAME&categories=CATEGORY_1,CATEGORY_2</code>. This will return all the clothings matching the first AND the second category.
  </li>
  <li><code>/clothings/{id}</code>: returns the clothing matching the given id variable.</li>
</ul>
<h4>categories:</h4>
<ul>
  <li><code>/categories</code>: returns all categories.</li>
  <li><code>/categories?name=ANY_NAME</code>: returns all categories matching the given "name" URL parameter.</li>
  <li><code>/categories/{id}</code>: returns the category matching the given id variable.</li>
</ul>
<h4>employees:</h4>
<ul>
  <li><code>/employees</code>: returns all employees.</li>
  <li><code>/employees?name=ANY_NAME</code>: returns all employees matching the given "name" URL parameter.</li>
  <li><code>/employees/{id}</code>: returns the employee matching the given id variable.</li>
</ul>
<h4>sales:</h4>
<ul>
  <li><code>/sales</code>: returns all sales.</li>
  <li><code>/sales/employee/{id}</code>: returns the sale matching the given employee id variable.</li>
  <li><code>/sales/{id}</code>: returns the sale matching the given id variable.</li>
</ul>

<h3><code>POST:</code></h3>
<h4>clothings:</h4>
<ul>
  <li><code>/clothings</code>: Inserts a clothing.</li>
</ul>
<h4>categories:</h4>
<ul>
  <li><code>/categories</code>: Inserts a category</li>
</ul>
<h4>employees:</h4>
<ul>
  <li><code>/employees</code>: Inserts a employee.</li>
</ul>
<h4>sales:</h4>
<ul>
  <li><code>/sales</code>: Inserts a sale</li>
</ul>

<h3><code>PUT:</code></h3>
<h4>clothings:</h4>
<ul>
  <li><code>/clothings/{id}</code>: Updates a clothing.</li>
</ul>
<h4>categories:</h4>
<ul>
  <li><code>/categories/{id}</code>: Updates a category.</li>
</ul>
<h4>employees:</h4>
<ul>
  <li><code>/employees/{id}</code>: Updates a employee.</li>
</ul>
<h4>sales:</h4>
<ul>
  <li><code>/sales/{id}</code>: Updates a sale.</li>
</ul>

<h3><code>DELETE:</code></h3>
<h4>clothings:</h4>
<ul>
  <li><code>/clothings/{id}</code>: Deletes a clothing.</li>
</ul>
<h4>categories:</h4>
<ul>
  <li><code>/categories/{id}</code>: Deletes a category.</li>
</ul>
<h4>employees:</h4>
<ul>
  <li><code>/employees/{id}</code>: Deletes a employee.</li>
</ul>
<h4>sales:</h4>
<ul>
  <li><code>/sales/{id}</code>: Deletes a sale.</li>
</ul>
