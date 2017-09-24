============
Introduction
============

Welcome! `Dataverse <http://dataverse.org>`_ is an `open source <https://github.com/IQSS/dataverse/blob/master/LICENSE.md>`_ project that loves `contributors <https://github.com/IQSS/dataverse/blob/master/CONTRIBUTING.md>`_!

.. contents:: |toctitle|
	:local:

Intended Audience
-----------------

This guide is intended primarily for developers who want to work on the main Dataverse code base at https://github.com/IQSS/dataverse

To get started, you'll want to set up your :doc:`dev-environment` and make sure you understand the branching strategy described in the :doc:`version-control` section. :doc:`testing` is expected. Opinions about :doc:`coding-style` are welcome!

If you have any questions at all, please reach out to other developers per https://github.com/IQSS/dataverse/blob/master/CONTRIBUTING.md

Roadmap
-------

For the Dataverse development roadmap, please see https://github.com/IQSS/dataverse/milestones

The `Contributing to Dataverse <https://github.com/IQSS/dataverse/blob/master/CONTRIBUTING.md>`_ document in the root of the source tree provides guidance on:

- the use of `labels <https://github.com/IQSS/dataverse/labels>`_ to organize and prioritize `issues <https://github.com/IQSS/dataverse/issues>`_ 
- making pull requests
- how to contact the development team

Related Guides
--------------

If you are a developer who wants to make use of Dataverse APIs, please see the :doc:`/api/index`. If you have front-end UI questions, please see the :doc:`/style/index`.

If you are a sysadmin who likes to code, you may be interested in hacking on installation scripts mentioned in the :doc:`/installation/index`. We validate the installation scripts with :doc:`/developers/tools` such as `Vagrant <http://vagrantup.com>`_.

Related Projects
----------------

As a developer, you also may be interested in these projects related to Dataverse:

- Miniverse - expose metrics from a Dataverse database: https://github.com/IQSS/miniverse
- `Zelig <http://zeligproject.org>`_ (R) - run statistical models on files uploaded to Dataverse: https://github.com/IQSS/Zelig
- `TwoRavens <http://2ra.vn>`_ (Javascript) - a `d3.js <http://d3js.org>`_ interface for exploring data and running Zelig models: https://github.com/IQSS/TwoRavens
- :doc:`/developers/unf/index` (Java) -  a Universal Numerical Fingerprint: https://github.com/IQSS/UNF
- `DataTags <https://github.com/IQSS/DataTags>`_ (Java and Scala) - tag datasets with privacy levels: https://github.com/IQSS/DataTags
- GeoConnect (Python) - create a map by uploading files to Dataverse: https://github.com/IQSS/geoconnect
- Dataverse API client libraries - use Dataverse APIs from various languages: :doc:`/api/client-libraries`
- Third party apps - make use of Dataverse APIs: :doc:`/api/apps`
- chat.dataverse.org - chat interface for Dataverse users and developers: https://github.com/IQSS/chat.dataverse.org
- [Your project here] :)

----

Next: :doc:`dev-environment`
