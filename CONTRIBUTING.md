# Contributing 👫

## Following our standards with git hooks 🌲

In this repository, we're following some kotlin/git conventions that all commits should follow:

* We're following
  the [conventional commits specification](https://www.conventionalcommits.org/en/v1.0.0/) and all the pull requests
  that we merge should be in that format.

For making it easier to work with this restrictions, we added git hooks for making sure that every commit follows this
standards, and that each file gets linted properly. For installing the tools you should do:

```shell
curl -sSLO https://github.com/pinterest/ktlint/releases/download/0.48.2/ktlint && chmod a+x ktlint && sudo mv ktlint /usr/local/bin/
cp .github/hooks/pre-commit .git/hooks/pre-commit
```

And now you will be following the standard and linting4 each file on each commit! 😇

## Issues 🚩

Issues are very valuable to this project.

- Ideas are a valuable source of contributions others can make
- Problems show where this project is lacking
- With a question you show where contributors can improve the user
  experience

Thank you for creating them.

## Pull Requests ❤️

Pull requests are, a great way to get your ideas into this repository.

When deciding if I merge in a pull request I look at the following
things:

### Does it state intent

You should be clear which problem you're trying to solve with your
contribution.

For example:

> feat(login): modify login function to use new api

Doesn't tell me anything about why you're doing that

> feat(login): modify login function to use new api because the old one is deprecated and will be removed in the next version

Tells me the problem that you have found, and the pull request shows me
the action you have taken to solve it.

### Is it of good quality

- There are no spelling mistakes
- It reads well
- For english language contributions: Has a good score on
  [Grammarly](https://www.grammarly.com) or [Hemingway
  App](https://www.hemingwayapp.com/)
