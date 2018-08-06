# Building an Algorithm for Logistic Regression in Java

## Description

### Syntax

```
java -jar runnable.jar src dst [lambda] [lim] [fit_intercept] [tol]
```

### Parameter

<table>
    <tr>
      <th>argument</th>
      <th>description</th>
      <th>type</th>
    </tr>
    <tr>
      <td><strong>src</strong></td>
      <td>data source, the file must be *.csv</td>
      <td>string</td>
    </tr>
    <tr>
      <td><strong>dst</strong></td>
      <td>result destination, the file would be *.txt, tab speared variable</td>
      <td>string</td>
    </tr>
    <tr>
      <td><strong>lambda</strong></td>
      <td>regularization strength, larger values specify stronger regularization</td>
      <td>float</td>
    </tr>
    <tr>
      <td><strong>lim</strong></td>
      <td>maximum number of iterations</td>
      <td>integer</td>
    </tr>
    <tr>
      <td><strong>fit_intercept</strong></td>
      <td>specifies if a constant should be added to the decision function</td>
      <td>boolean</td>
    </tr>
    <tr>
      <td><strong>tol</strong></td>
      <td>tolerance for stopping criteria</td>
      <td>float</td>
    </tr>
</table>

### Example

```
java -jar logist.jar data.csv result.txt
```

## Class Diagram
<img src="doc/logistic (3).png">

## Algorithm
see also <a href="algorithm_doc.ipynb">algorithm doc</a> 
