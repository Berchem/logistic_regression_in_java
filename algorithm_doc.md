
# Parameter estimation in probibilistic model

Assume data generate via a probabilistic model :
\begin{align}
d \sim P(d|\theta)
\end{align}

$P(d|\theta)$ : Probability distribution underlying the data
* $\theta$ : fixed, but unknown distribution parameter

Given $n$ independent and identically distributed samples of data $D$ :
\begin{align}
D = \{d_1, d_2, d_3, ..., d_n\}
\end{align}

To estimate parameter $\theta$ that optimum description to the data, we could use the maximum-a-posteriori estimation method.
<p><br><br></p>


## The maximum-a-posteriori estimation

**Maximum-a-Posteriori** (**MAP**) is to choose $\theta$ which maximizes the posterior probability of $\theta$.

**Posterior probability of $\theta$** is given by the Bayes Rule :
\begin{align}
P(\theta|D) = \frac{P(D|\theta)P(\theta)}{P(D)}
\end{align}

where, 
> $p(D|\theta)$ : **likelihood function**
>
> $p(\theta)$ : **prior probapility of $\theta$**, without having seen any data
>
> $p(D)$ : **probapility of data**, independent of $\theta$

**Posterior probability of $\theta$** could be driven as :

\begin{align}
\hat{\theta}_{MAP} = {\arg\max}_{\theta}P(\theta|D) &= {\arg\max}_{\theta}\frac{P(D|\theta)P(\theta)}{P(D)}\\
\text{probapility of data is constant  }\Rightarrow&= {\arg\max}_{\theta}P(D|\theta)P(\theta)\\
\text{the natural logarithm is a strictly increasing function  }\Rightarrow&= {\arg\max}_{\theta}log(P(D|\theta)P(\theta))\\
\text{the strictly increasing function is, if }x_1 < x_2,\text{ then }f(x_1) < f(x_2)\Rightarrow&= {\arg\max}_{\theta}[logP(D|\theta) + logP(\theta)]\\
\hat{\theta}_{MAP} &= {\arg\max}_{\theta}[{\sum}logP(d_i|\theta) + logP(\theta)]
\end{align}

## To find the maxima of posteriori probability function

Given $f(x)$, we **differentiate once** to find $f'(x)$. 

Set $f'(x) = 0$ and solve for $x$. Using our above observation, the x values we find are the $x$-coordinates of our maxima and minima.

Substitute these $x$-values back into $f(x)$. This gives the corresponding $y$-coordinates of our maxima or minima.

Since, to find the maxima of posterior probability function is equivalent **to find the root of the gradient of posteriori probability function**.

Then use of the gradient of the objective and Hessian matrix to converge in Newton's iterative method to find the maxima of posterior probability function.
<p><br><br></p>

## Newton's method
Newton's method is a method for finding successively better approximations to the roots (or zeroes) of a real-valued function.

\begin{align}
x:f(x)=0
\end{align}

The process is repeated as :

\begin{align}
x_{new} = x_{old} - \frac{f(x_{old})}{f'(x_{old})}
\end{align}

while $x_{new}$ close enough to $x_{old}$, &nbsp;$x_{new}{\approx}x_{old}$, &nbsp;the sequence converged, &nbsp;$x_{new}$ is approximate to the root $x$ of the function $f(x)$.

In programing, we could code that as:
```python
if abs(w_new - w_old) < tolerance  # given a tolerance = 1e-8
    break
```

>known a curve :
>
>\begin{align}
y = f(x)
\end{align}
>
>a point at $x_n$ had a $y_n$ :
>
>\begin{align}
y_n = f(x_n)
\end{align}
>
>and another point $x$ approximate to $x_n$ had a $y$ :
>
>\begin{align}
y = f(x_n) + \frac{f(x) - f(x_n)}{(x - x_n)}(x - x_n)
\end{align}
>
>the $\frac{f(x) - f(x_n)}{(x - x_n)}$ is the tangent line at $x_n$
>
>\begin{align}
y = f(x_n) + f'(x_n)(x - x_n)
\end{align}
>
>where $f'$ denotes the derivative of the function $f$
>
>the root, the value of $x$ such that $y = 0$, is then used as the next approximation to the root, $x_{n+1}$ : 
>
>\begin{align}
0 = f(x_n) + f'(x_n)(x_{n+1} - x_n)
\end{align}
>
>solving for $x_{n+1}$ gives
>\begin{align}
x_{n+1} = x_n - \frac{f(x_n)}{f'(x_n)}
\end{align}
<p><br><br></p>


# Logistic Regression

The logistic regression model is :

\begin{align}
p(y=\pm1|\mathbf{x}, \mathbf{w}) & = \frac{1}{1+e^{-y\mathbf{w}^T\mathbf{x}}} 
\end{align}

A common prior to use with MAP is :

\begin{align}
p(\mathbf{w}) & \sim \mathcal{N}(0, \lambda^{-1}\mathbf{I}) 
\end{align}

where $\lambda$ is regularization strength

Given a data set $(\mathbf{X}, \mathbf{y}) = [(\mathbf{x}_1, y_1), (\mathbf{x}_2, y_2), ..., (\mathbf{x}_n, y_n)]$, we want to find the parameter vector $\mathbf{w}$ which maximizes :

\begin{align}
l(\mathbf{w}) = -\sum^{n}_{i=1}log(1+e^{-y_i\mathbf{w}^T\mathbf{x}_i}) - \frac{\lambda}{2}{\mathbf{w}^T\mathbf{w}}
\end{align}



>Driven :
>
>data set $(\mathbf{X}, \mathbf{y}) = [(\mathbf{x}_1, y_1), (\mathbf{x}_2, y_2), ..., (\mathbf{x}_n, y_n)]$ could be seen as $D = \{d_1, d_2, d_3, ..., d_n\}$
>
>the parameter vector $\mathbf{w}$ was $\theta$
>
>so the maximum posterior probability of the parameter vector $\mathbf{w}$ could be written as : 
>
>\begin{align}
\mathbf{w}_{MAP} &= {\arg\max}_{\theta}[\sum^{n}_{i=1}logP(y_i, \mathbf{x}_{i}|\mathbf{w}) + logP(\mathbf{w})]
\end{align}
>
>\begin{align}
{{\mathbf{w}}}_{MAP} &= {\arg\max}_{\mathbf{w}}\sum^{n}_{i=1}log(\frac{1}{1+e^{-y_i{{\mathbf{w}}}^T{{\mathbf{x}}}_i}}) + log(\frac{1}{\sqrt{\frac{2\pi}{\lambda}}}e^{-\frac{\lambda {\mathbf{w}}^T{\mathbf{w}}}{2}})\\
&= {\arg\max}_{\mathbf{w}}\sum^{n}_{i=1}log({1+e^{-y_i{\mathbf{w}}^T{\mathbf{x}}_i}})^{-1} + [log(\frac{1}{\sqrt{\frac{2\pi}{\lambda}}}) + log(e^{-\frac{\lambda \mathbf{w}^T{\mathbf{w}}}{2}})]\\
&= {\arg\max}_{\mathbf{w}}-\sum^{n}_{i=1}log({1+e^{-y_i{\mathbf{w}}^T{\mathbf{x}}_i}}) - \frac{\lambda}{2}{\mathbf{w}}^T{\mathbf{w}}\\
\end{align}


The posterior probability function : 

\begin{align}
l(\mathbf{w}) = -{\sum}^{n}_{i=1}log(1+e^{-y_i\mathbf{w}^T\mathbf{x}_i}) - \frac{\lambda}{2}{\mathbf{w}}^T{{\mathbf{w}}}
\end{align}

The gradient of the objective, **first-order partial derivatives** of the posterior function :

\begin{align}
\mathbf{g} = \triangledown_{\mathbf{w}}l(\mathbf{w}) = \sum^{n}_{i=1}(1-\frac{1}{1+e^{-y_i\mathbf{w}^T\mathbf{x}_i}})y_i\mathbf{x}_i - \lambda\mathbf{w}
\end{align}

The Hessian matrix, a square matrix of **second-order partial derivatives** of the posterior function :

\begin{align}
\mathbf{H} = \frac{d^2l({\mathbf{w}})}{d{\mathbf{w}}d{\mathbf{w}}^T} = -\sum^{n}_{i=1}(\frac{1}{1+e^{-{\mathbf{w}}^T{\mathbf{x}}_i}})(1-\frac{1}{1+e^{-{\mathbf{w}}^T{\mathbf{x}}_i}}){\mathbf{x}}_i{\mathbf{x}}^T_i - \lambda\mathbf{I}
\end{align}

which in matrix form can be written :

\begin{align}
\mathbf{H} = -\mathbf{XAX}^{T} - \lambda\mathbf{I}
\end{align}

where $\mathbf{A}$ is a diagonal matrix :
\begin{align}
a_{ii} = \frac{1}{1+e^{{\mathbf{w}}^T{\mathbf{x}}_i}}(1-\frac{1}{1+e^{{\mathbf{w}}^T{\mathbf{x}}_i}})
\end{align}
<p><br><br></p>

We have known all of the algorithm details now, let's try organizing the equations to code.
<br><br>
```python
# if we had defined the variable x, y, w_old, w_new, lamb, tol, lim, i
# x.shape = (m, n), <type 'numpy.ndarray'>
# y.shape = (m, 1), <type 'numpy.ndarray'>
# w_old.shape = (n, 1), <type 'numpy.'>
# w_new.shape = (n, 1), <type 'numpy.ndarray'>
# lamb.shape = (n, n), <type 'numpy.ndarray'>, 
#     take the regularization parameter lambda's name as lamb, 
#     because lambda is the keyword for anonymous function in python.
# tol, <type 'float'>, the tolerance for difference between parameter i + 1 and i
# lim, <type 'int'>, maxima iterations
# i, <type 'int'>, counts of iterations


def sigmoid(x):
    return 1.0 / (1 + np.exp(-x))


def sigmoid_prime(x):
    return sigmoid(x) * (1 - sigmoid(x))


def hessian(X, A, lamb):
    return -X.T.dot(A).dot(X) + lamb


while i < iter_lim:
    # update parameter estimation
    w_old = w_new
    
    # calculate diagonal matrix, that a is the A_ii
    a = sigmoid_prime(x.dot(w_old))
    A = np.diagflat(a)
    
    # Hessian matrix
    H = hessian(x, A, lamb)
    
    # a part of gradient objective and combine previous iterated parameter
    z = x.dot(w_old) + (1 - sigmoid(y * x.dot(w_old))) * y / a
    XAz = x.T.dot(A).dot(z)
    
    # calculate current parameter
    w_new = np.linalg.inv(-H).dot(XAz)
    
    # calculate tolerance and iteration time to break loop
    if all(abs(w - w_old) < tol)::
        break
        
    else:
        i += 1
```
<p><br><br></p>

# Reference
* Minka, T. P. (2003). A comparison of numerical optimizers for logistic regression. Unpublished draft.
* Grus, J. (2015). Data science from scratch: first principles with python (pp.270-279). " O'Reilly Media, Inc.".
* The Pennsylvania State University, Department of Statistics Online Programs. (n.d.) Retrieved June 29, 2018 from [online course](https://onlinecourses.science.psu.edu/stat414/node/191/)
