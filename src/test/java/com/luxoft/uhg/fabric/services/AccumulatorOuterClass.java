// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service/Accumulator.proto

package com.luxoft.uhg.fabric.services;

public final class AccumulatorOuterClass {
  private AccumulatorOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  /**
   * Protobuf service {@code Accumulator}
   */
  public static abstract class Accumulator
      implements com.google.protobuf.Service {
    protected Accumulator() {}

    public interface Interface {
      /**
       * <code>rpc AddPlan(.main.Plan) returns (.google.protobuf.Empty);</code>
       */
      public abstract void addPlan(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.Plan request,
          com.google.protobuf.RpcCallback<com.google.protobuf.Empty> done);

      /**
       * <code>rpc AddMember(.main.AddMember) returns (.google.protobuf.Empty);</code>
       */
      public abstract void addMember(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.AddMember request,
          com.google.protobuf.RpcCallback<com.google.protobuf.Empty> done);

      /**
       * <code>rpc AddClaim(.main.AddClaim) returns (.google.protobuf.Empty);</code>
       */
      public abstract void addClaim(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.AddClaim request,
          com.google.protobuf.RpcCallback<com.google.protobuf.Empty> done);

      /**
       * <code>rpc GetAccumulator(.main.GetAccumulator) returns (.main.Accumulator);</code>
       */
      public abstract void getAccumulator(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator request,
          com.google.protobuf.RpcCallback<com.luxoft.uhg.fabric.proto.ClaimAccumulator.Accumulator> done);

      /**
       * <code>rpc GetPlan(.main.GetPlan) returns (.main.Plan);</code>
       */
      public abstract void getPlan(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetPlan request,
          com.google.protobuf.RpcCallback<com.luxoft.uhg.fabric.proto.ClaimAccumulator.Plan> done);

      /**
       * <code>rpc GetEvents(.main.GetAccumulator) returns (.main.Events);</code>
       */
      public abstract void getEvents(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator request,
          com.google.protobuf.RpcCallback<com.luxoft.uhg.fabric.proto.ClaimAccumulator.Events> done);

      /**
       * <code>rpc GetHistory(.main.GetAccumulator) returns (.main.History);</code>
       */
      public abstract void getHistory(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator request,
          com.google.protobuf.RpcCallback<com.luxoft.uhg.fabric.proto.ClaimAccumulator.History> done);

    }

    public static com.google.protobuf.Service newReflectiveService(
        final Interface impl) {
      return new Accumulator() {
        @java.lang.Override
        public  void addPlan(
            com.google.protobuf.RpcController controller,
            com.luxoft.uhg.fabric.proto.ClaimAccumulator.Plan request,
            com.google.protobuf.RpcCallback<com.google.protobuf.Empty> done) {
          impl.addPlan(controller, request, done);
        }

        @java.lang.Override
        public  void addMember(
            com.google.protobuf.RpcController controller,
            com.luxoft.uhg.fabric.proto.ClaimAccumulator.AddMember request,
            com.google.protobuf.RpcCallback<com.google.protobuf.Empty> done) {
          impl.addMember(controller, request, done);
        }

        @java.lang.Override
        public  void addClaim(
            com.google.protobuf.RpcController controller,
            com.luxoft.uhg.fabric.proto.ClaimAccumulator.AddClaim request,
            com.google.protobuf.RpcCallback<com.google.protobuf.Empty> done) {
          impl.addClaim(controller, request, done);
        }

        @java.lang.Override
        public  void getAccumulator(
            com.google.protobuf.RpcController controller,
            com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator request,
            com.google.protobuf.RpcCallback<com.luxoft.uhg.fabric.proto.ClaimAccumulator.Accumulator> done) {
          impl.getAccumulator(controller, request, done);
        }

        @java.lang.Override
        public  void getPlan(
            com.google.protobuf.RpcController controller,
            com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetPlan request,
            com.google.protobuf.RpcCallback<com.luxoft.uhg.fabric.proto.ClaimAccumulator.Plan> done) {
          impl.getPlan(controller, request, done);
        }

        @java.lang.Override
        public  void getEvents(
            com.google.protobuf.RpcController controller,
            com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator request,
            com.google.protobuf.RpcCallback<com.luxoft.uhg.fabric.proto.ClaimAccumulator.Events> done) {
          impl.getEvents(controller, request, done);
        }

        @java.lang.Override
        public  void getHistory(
            com.google.protobuf.RpcController controller,
            com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator request,
            com.google.protobuf.RpcCallback<com.luxoft.uhg.fabric.proto.ClaimAccumulator.History> done) {
          impl.getHistory(controller, request, done);
        }

      };
    }

    public static com.google.protobuf.BlockingService
        newReflectiveBlockingService(final BlockingInterface impl) {
      return new com.google.protobuf.BlockingService() {
        public final com.google.protobuf.Descriptors.ServiceDescriptor
            getDescriptorForType() {
          return getDescriptor();
        }

        public final com.google.protobuf.Message callBlockingMethod(
            com.google.protobuf.Descriptors.MethodDescriptor method,
            com.google.protobuf.RpcController controller,
            com.google.protobuf.Message request)
            throws com.google.protobuf.ServiceException {
          if (method.getService() != getDescriptor()) {
            throw new java.lang.IllegalArgumentException(
              "Service.callBlockingMethod() given method descriptor for " +
              "wrong service type.");
          }
          switch(method.getIndex()) {
            case 0:
              return impl.addPlan(controller, (com.luxoft.uhg.fabric.proto.ClaimAccumulator.Plan)request);
            case 1:
              return impl.addMember(controller, (com.luxoft.uhg.fabric.proto.ClaimAccumulator.AddMember)request);
            case 2:
              return impl.addClaim(controller, (com.luxoft.uhg.fabric.proto.ClaimAccumulator.AddClaim)request);
            case 3:
              return impl.getAccumulator(controller, (com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator)request);
            case 4:
              return impl.getPlan(controller, (com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetPlan)request);
            case 5:
              return impl.getEvents(controller, (com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator)request);
            case 6:
              return impl.getHistory(controller, (com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator)request);
            default:
              throw new java.lang.AssertionError("Can't get here.");
          }
        }

        public final com.google.protobuf.Message
            getRequestPrototype(
            com.google.protobuf.Descriptors.MethodDescriptor method) {
          if (method.getService() != getDescriptor()) {
            throw new java.lang.IllegalArgumentException(
              "Service.getRequestPrototype() given method " +
              "descriptor for wrong service type.");
          }
          switch(method.getIndex()) {
            case 0:
              return com.luxoft.uhg.fabric.proto.ClaimAccumulator.Plan.getDefaultInstance();
            case 1:
              return com.luxoft.uhg.fabric.proto.ClaimAccumulator.AddMember.getDefaultInstance();
            case 2:
              return com.luxoft.uhg.fabric.proto.ClaimAccumulator.AddClaim.getDefaultInstance();
            case 3:
              return com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator.getDefaultInstance();
            case 4:
              return com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetPlan.getDefaultInstance();
            case 5:
              return com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator.getDefaultInstance();
            case 6:
              return com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator.getDefaultInstance();
            default:
              throw new java.lang.AssertionError("Can't get here.");
          }
        }

        public final com.google.protobuf.Message
            getResponsePrototype(
            com.google.protobuf.Descriptors.MethodDescriptor method) {
          if (method.getService() != getDescriptor()) {
            throw new java.lang.IllegalArgumentException(
              "Service.getResponsePrototype() given method " +
              "descriptor for wrong service type.");
          }
          switch(method.getIndex()) {
            case 0:
              return com.google.protobuf.Empty.getDefaultInstance();
            case 1:
              return com.google.protobuf.Empty.getDefaultInstance();
            case 2:
              return com.google.protobuf.Empty.getDefaultInstance();
            case 3:
              return com.luxoft.uhg.fabric.proto.ClaimAccumulator.Accumulator.getDefaultInstance();
            case 4:
              return com.luxoft.uhg.fabric.proto.ClaimAccumulator.Plan.getDefaultInstance();
            case 5:
              return com.luxoft.uhg.fabric.proto.ClaimAccumulator.Events.getDefaultInstance();
            case 6:
              return com.luxoft.uhg.fabric.proto.ClaimAccumulator.History.getDefaultInstance();
            default:
              throw new java.lang.AssertionError("Can't get here.");
          }
        }

      };
    }

    /**
     * <code>rpc AddPlan(.main.Plan) returns (.google.protobuf.Empty);</code>
     */
    public abstract void addPlan(
        com.google.protobuf.RpcController controller,
        com.luxoft.uhg.fabric.proto.ClaimAccumulator.Plan request,
        com.google.protobuf.RpcCallback<com.google.protobuf.Empty> done);

    /**
     * <code>rpc AddMember(.main.AddMember) returns (.google.protobuf.Empty);</code>
     */
    public abstract void addMember(
        com.google.protobuf.RpcController controller,
        com.luxoft.uhg.fabric.proto.ClaimAccumulator.AddMember request,
        com.google.protobuf.RpcCallback<com.google.protobuf.Empty> done);

    /**
     * <code>rpc AddClaim(.main.AddClaim) returns (.google.protobuf.Empty);</code>
     */
    public abstract void addClaim(
        com.google.protobuf.RpcController controller,
        com.luxoft.uhg.fabric.proto.ClaimAccumulator.AddClaim request,
        com.google.protobuf.RpcCallback<com.google.protobuf.Empty> done);

    /**
     * <code>rpc GetAccumulator(.main.GetAccumulator) returns (.main.Accumulator);</code>
     */
    public abstract void getAccumulator(
        com.google.protobuf.RpcController controller,
        com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator request,
        com.google.protobuf.RpcCallback<com.luxoft.uhg.fabric.proto.ClaimAccumulator.Accumulator> done);

    /**
     * <code>rpc GetPlan(.main.GetPlan) returns (.main.Plan);</code>
     */
    public abstract void getPlan(
        com.google.protobuf.RpcController controller,
        com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetPlan request,
        com.google.protobuf.RpcCallback<com.luxoft.uhg.fabric.proto.ClaimAccumulator.Plan> done);

    /**
     * <code>rpc GetEvents(.main.GetAccumulator) returns (.main.Events);</code>
     */
    public abstract void getEvents(
        com.google.protobuf.RpcController controller,
        com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator request,
        com.google.protobuf.RpcCallback<com.luxoft.uhg.fabric.proto.ClaimAccumulator.Events> done);

    /**
     * <code>rpc GetHistory(.main.GetAccumulator) returns (.main.History);</code>
     */
    public abstract void getHistory(
        com.google.protobuf.RpcController controller,
        com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator request,
        com.google.protobuf.RpcCallback<com.luxoft.uhg.fabric.proto.ClaimAccumulator.History> done);

    public static final
        com.google.protobuf.Descriptors.ServiceDescriptor
        getDescriptor() {
      return com.luxoft.uhg.fabric.services.AccumulatorOuterClass.getDescriptor().getServices().get(0);
    }
    public final com.google.protobuf.Descriptors.ServiceDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }

    public final void callMethod(
        com.google.protobuf.Descriptors.MethodDescriptor method,
        com.google.protobuf.RpcController controller,
        com.google.protobuf.Message request,
        com.google.protobuf.RpcCallback<
          com.google.protobuf.Message> done) {
      if (method.getService() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "Service.callMethod() given method descriptor for wrong " +
          "service type.");
      }
      switch(method.getIndex()) {
        case 0:
          this.addPlan(controller, (com.luxoft.uhg.fabric.proto.ClaimAccumulator.Plan)request,
            com.google.protobuf.RpcUtil.<com.google.protobuf.Empty>specializeCallback(
              done));
          return;
        case 1:
          this.addMember(controller, (com.luxoft.uhg.fabric.proto.ClaimAccumulator.AddMember)request,
            com.google.protobuf.RpcUtil.<com.google.protobuf.Empty>specializeCallback(
              done));
          return;
        case 2:
          this.addClaim(controller, (com.luxoft.uhg.fabric.proto.ClaimAccumulator.AddClaim)request,
            com.google.protobuf.RpcUtil.<com.google.protobuf.Empty>specializeCallback(
              done));
          return;
        case 3:
          this.getAccumulator(controller, (com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator)request,
            com.google.protobuf.RpcUtil.<com.luxoft.uhg.fabric.proto.ClaimAccumulator.Accumulator>specializeCallback(
              done));
          return;
        case 4:
          this.getPlan(controller, (com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetPlan)request,
            com.google.protobuf.RpcUtil.<com.luxoft.uhg.fabric.proto.ClaimAccumulator.Plan>specializeCallback(
              done));
          return;
        case 5:
          this.getEvents(controller, (com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator)request,
            com.google.protobuf.RpcUtil.<com.luxoft.uhg.fabric.proto.ClaimAccumulator.Events>specializeCallback(
              done));
          return;
        case 6:
          this.getHistory(controller, (com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator)request,
            com.google.protobuf.RpcUtil.<com.luxoft.uhg.fabric.proto.ClaimAccumulator.History>specializeCallback(
              done));
          return;
        default:
          throw new java.lang.AssertionError("Can't get here.");
      }
    }

    public final com.google.protobuf.Message
        getRequestPrototype(
        com.google.protobuf.Descriptors.MethodDescriptor method) {
      if (method.getService() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "Service.getRequestPrototype() given method " +
          "descriptor for wrong service type.");
      }
      switch(method.getIndex()) {
        case 0:
          return com.luxoft.uhg.fabric.proto.ClaimAccumulator.Plan.getDefaultInstance();
        case 1:
          return com.luxoft.uhg.fabric.proto.ClaimAccumulator.AddMember.getDefaultInstance();
        case 2:
          return com.luxoft.uhg.fabric.proto.ClaimAccumulator.AddClaim.getDefaultInstance();
        case 3:
          return com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator.getDefaultInstance();
        case 4:
          return com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetPlan.getDefaultInstance();
        case 5:
          return com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator.getDefaultInstance();
        case 6:
          return com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator.getDefaultInstance();
        default:
          throw new java.lang.AssertionError("Can't get here.");
      }
    }

    public final com.google.protobuf.Message
        getResponsePrototype(
        com.google.protobuf.Descriptors.MethodDescriptor method) {
      if (method.getService() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "Service.getResponsePrototype() given method " +
          "descriptor for wrong service type.");
      }
      switch(method.getIndex()) {
        case 0:
          return com.google.protobuf.Empty.getDefaultInstance();
        case 1:
          return com.google.protobuf.Empty.getDefaultInstance();
        case 2:
          return com.google.protobuf.Empty.getDefaultInstance();
        case 3:
          return com.luxoft.uhg.fabric.proto.ClaimAccumulator.Accumulator.getDefaultInstance();
        case 4:
          return com.luxoft.uhg.fabric.proto.ClaimAccumulator.Plan.getDefaultInstance();
        case 5:
          return com.luxoft.uhg.fabric.proto.ClaimAccumulator.Events.getDefaultInstance();
        case 6:
          return com.luxoft.uhg.fabric.proto.ClaimAccumulator.History.getDefaultInstance();
        default:
          throw new java.lang.AssertionError("Can't get here.");
      }
    }

    public static Stub newStub(
        com.google.protobuf.RpcChannel channel) {
      return new Stub(channel);
    }

    public static final class Stub extends com.luxoft.uhg.fabric.services.AccumulatorOuterClass.Accumulator implements Interface {
      private Stub(com.google.protobuf.RpcChannel channel) {
        this.channel = channel;
      }

      private final com.google.protobuf.RpcChannel channel;

      public com.google.protobuf.RpcChannel getChannel() {
        return channel;
      }

      public  void addPlan(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.Plan request,
          com.google.protobuf.RpcCallback<com.google.protobuf.Empty> done) {
        channel.callMethod(
          getDescriptor().getMethods().get(0),
          controller,
          request,
          com.google.protobuf.Empty.getDefaultInstance(),
          com.google.protobuf.RpcUtil.generalizeCallback(
            done,
            com.google.protobuf.Empty.class,
            com.google.protobuf.Empty.getDefaultInstance()));
      }

      public  void addMember(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.AddMember request,
          com.google.protobuf.RpcCallback<com.google.protobuf.Empty> done) {
        channel.callMethod(
          getDescriptor().getMethods().get(1),
          controller,
          request,
          com.google.protobuf.Empty.getDefaultInstance(),
          com.google.protobuf.RpcUtil.generalizeCallback(
            done,
            com.google.protobuf.Empty.class,
            com.google.protobuf.Empty.getDefaultInstance()));
      }

      public  void addClaim(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.AddClaim request,
          com.google.protobuf.RpcCallback<com.google.protobuf.Empty> done) {
        channel.callMethod(
          getDescriptor().getMethods().get(2),
          controller,
          request,
          com.google.protobuf.Empty.getDefaultInstance(),
          com.google.protobuf.RpcUtil.generalizeCallback(
            done,
            com.google.protobuf.Empty.class,
            com.google.protobuf.Empty.getDefaultInstance()));
      }

      public  void getAccumulator(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator request,
          com.google.protobuf.RpcCallback<com.luxoft.uhg.fabric.proto.ClaimAccumulator.Accumulator> done) {
        channel.callMethod(
          getDescriptor().getMethods().get(3),
          controller,
          request,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.Accumulator.getDefaultInstance(),
          com.google.protobuf.RpcUtil.generalizeCallback(
            done,
            com.luxoft.uhg.fabric.proto.ClaimAccumulator.Accumulator.class,
            com.luxoft.uhg.fabric.proto.ClaimAccumulator.Accumulator.getDefaultInstance()));
      }

      public  void getPlan(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetPlan request,
          com.google.protobuf.RpcCallback<com.luxoft.uhg.fabric.proto.ClaimAccumulator.Plan> done) {
        channel.callMethod(
          getDescriptor().getMethods().get(4),
          controller,
          request,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.Plan.getDefaultInstance(),
          com.google.protobuf.RpcUtil.generalizeCallback(
            done,
            com.luxoft.uhg.fabric.proto.ClaimAccumulator.Plan.class,
            com.luxoft.uhg.fabric.proto.ClaimAccumulator.Plan.getDefaultInstance()));
      }

      public  void getEvents(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator request,
          com.google.protobuf.RpcCallback<com.luxoft.uhg.fabric.proto.ClaimAccumulator.Events> done) {
        channel.callMethod(
          getDescriptor().getMethods().get(5),
          controller,
          request,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.Events.getDefaultInstance(),
          com.google.protobuf.RpcUtil.generalizeCallback(
            done,
            com.luxoft.uhg.fabric.proto.ClaimAccumulator.Events.class,
            com.luxoft.uhg.fabric.proto.ClaimAccumulator.Events.getDefaultInstance()));
      }

      public  void getHistory(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator request,
          com.google.protobuf.RpcCallback<com.luxoft.uhg.fabric.proto.ClaimAccumulator.History> done) {
        channel.callMethod(
          getDescriptor().getMethods().get(6),
          controller,
          request,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.History.getDefaultInstance(),
          com.google.protobuf.RpcUtil.generalizeCallback(
            done,
            com.luxoft.uhg.fabric.proto.ClaimAccumulator.History.class,
            com.luxoft.uhg.fabric.proto.ClaimAccumulator.History.getDefaultInstance()));
      }
    }

    public static BlockingInterface newBlockingStub(
        com.google.protobuf.BlockingRpcChannel channel) {
      return new BlockingStub(channel);
    }

    public interface BlockingInterface {
      public com.google.protobuf.Empty addPlan(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.Plan request)
          throws com.google.protobuf.ServiceException;

      public com.google.protobuf.Empty addMember(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.AddMember request)
          throws com.google.protobuf.ServiceException;

      public com.google.protobuf.Empty addClaim(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.AddClaim request)
          throws com.google.protobuf.ServiceException;

      public com.luxoft.uhg.fabric.proto.ClaimAccumulator.Accumulator getAccumulator(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator request)
          throws com.google.protobuf.ServiceException;

      public com.luxoft.uhg.fabric.proto.ClaimAccumulator.Plan getPlan(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetPlan request)
          throws com.google.protobuf.ServiceException;

      public com.luxoft.uhg.fabric.proto.ClaimAccumulator.Events getEvents(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator request)
          throws com.google.protobuf.ServiceException;

      public com.luxoft.uhg.fabric.proto.ClaimAccumulator.History getHistory(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator request)
          throws com.google.protobuf.ServiceException;
    }

    private static final class BlockingStub implements BlockingInterface {
      private BlockingStub(com.google.protobuf.BlockingRpcChannel channel) {
        this.channel = channel;
      }

      private final com.google.protobuf.BlockingRpcChannel channel;

      public com.google.protobuf.Empty addPlan(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.Plan request)
          throws com.google.protobuf.ServiceException {
        return (com.google.protobuf.Empty) channel.callBlockingMethod(
          getDescriptor().getMethods().get(0),
          controller,
          request,
          com.google.protobuf.Empty.getDefaultInstance());
      }


      public com.google.protobuf.Empty addMember(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.AddMember request)
          throws com.google.protobuf.ServiceException {
        return (com.google.protobuf.Empty) channel.callBlockingMethod(
          getDescriptor().getMethods().get(1),
          controller,
          request,
          com.google.protobuf.Empty.getDefaultInstance());
      }


      public com.google.protobuf.Empty addClaim(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.AddClaim request)
          throws com.google.protobuf.ServiceException {
        return (com.google.protobuf.Empty) channel.callBlockingMethod(
          getDescriptor().getMethods().get(2),
          controller,
          request,
          com.google.protobuf.Empty.getDefaultInstance());
      }


      public com.luxoft.uhg.fabric.proto.ClaimAccumulator.Accumulator getAccumulator(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator request)
          throws com.google.protobuf.ServiceException {
        return (com.luxoft.uhg.fabric.proto.ClaimAccumulator.Accumulator) channel.callBlockingMethod(
          getDescriptor().getMethods().get(3),
          controller,
          request,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.Accumulator.getDefaultInstance());
      }


      public com.luxoft.uhg.fabric.proto.ClaimAccumulator.Plan getPlan(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetPlan request)
          throws com.google.protobuf.ServiceException {
        return (com.luxoft.uhg.fabric.proto.ClaimAccumulator.Plan) channel.callBlockingMethod(
          getDescriptor().getMethods().get(4),
          controller,
          request,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.Plan.getDefaultInstance());
      }


      public com.luxoft.uhg.fabric.proto.ClaimAccumulator.Events getEvents(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator request)
          throws com.google.protobuf.ServiceException {
        return (com.luxoft.uhg.fabric.proto.ClaimAccumulator.Events) channel.callBlockingMethod(
          getDescriptor().getMethods().get(5),
          controller,
          request,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.Events.getDefaultInstance());
      }


      public com.luxoft.uhg.fabric.proto.ClaimAccumulator.History getHistory(
          com.google.protobuf.RpcController controller,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.GetAccumulator request)
          throws com.google.protobuf.ServiceException {
        return (com.luxoft.uhg.fabric.proto.ClaimAccumulator.History) channel.callBlockingMethod(
          getDescriptor().getMethods().get(6),
          controller,
          request,
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.History.getDefaultInstance());
      }

    }

    // @@protoc_insertion_point(class_scope:Accumulator)
  }


  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\031service/Accumulator.proto\032\rxmlcall.pro" +
      "to\032\033google/protobuf/empty.proto\032\032message" +
      "s/Accumulator.proto2\225\003\n\013Accumulator\0223\n\007A" +
      "ddPlan\022\n.main.Plan\032\026.google.protobuf.Emp" +
      "ty\"\004\220\202\031\002\022:\n\tAddMember\022\017.main.AddMember\032\026" +
      ".google.protobuf.Empty\"\004\220\202\031\002\0228\n\010AddClaim" +
      "\022\016.main.AddClaim\032\026.google.protobuf.Empty" +
      "\"\004\220\202\031\002\022?\n\016GetAccumulator\022\024.main.GetAccum" +
      "ulator\032\021.main.Accumulator\"\004\220\202\031\001\022*\n\007GetPl" +
      "an\022\r.main.GetPlan\032\n.main.Plan\"\004\220\202\031\001\0225\n\tG",
      "etEvents\022\024.main.GetAccumulator\032\014.main.Ev" +
      "ents\"\004\220\202\031\001\0227\n\nGetHistory\022\024.main.GetAccum" +
      "ulator\032\r.main.History\"\004\220\202\031\001B#\n\036com.luxof" +
      "t.uhg.fabric.services\210\001\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.luxoft.xmlcall.proto.XmlCall.getDescriptor(),
          com.google.protobuf.EmptyProto.getDescriptor(),
          com.luxoft.uhg.fabric.proto.ClaimAccumulator.getDescriptor(),
        }, assigner);
    com.google.protobuf.ExtensionRegistry registry =
        com.google.protobuf.ExtensionRegistry.newInstance();
    registry.add(com.luxoft.xmlcall.proto.XmlCall.execType);
    com.google.protobuf.Descriptors.FileDescriptor
        .internalUpdateFileDescriptor(descriptor, registry);
    com.luxoft.xmlcall.proto.XmlCall.getDescriptor();
    com.google.protobuf.EmptyProto.getDescriptor();
    com.luxoft.uhg.fabric.proto.ClaimAccumulator.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
